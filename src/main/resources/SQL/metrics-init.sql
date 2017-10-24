CREATE OR REPLACE FUNCTION UPDATE_METRICS() RETURNS BOOLEAN AS '
	DECLARE
	start_date bigint := extract(''epoch'' from date_trunc(''YEAR'', (CURRENT_DATE - INTERVAL ''1 YEAR''))::TIMESTAMP)::bigint * 1000;
	end_date bigint := extract(''epoch'' from (date_trunc(''YEAR'', (CURRENT_DATE + INTERVAL ''1 YEAR'')) - INTERVAL ''1 SEC'')::TIMESTAMP)::bigint * 1000;
    BEGIN
        /* STEP 1: Query to clear deployment metrics table */
	DELETE FROM deployment_metrics;

	/* STEP 2: Populate deployment metrics table with deployments that have expiry date in the last two years */
	INSERT INTO deployment_metrics (
	  deployment_id,
	  catalog_base_price,
	  customer,
	  deployer,
	  distributor,
	  manufacturer,
	  customer_name,
	  deployer_name,
	  distributor_name,
	  manufacturer_name,
	  parent_customer_name,
	  parent_deployer_name,
	  parent_distributor_name,
	  parent_manufacturer_name,
	  expiry,
	  pendingpodistributor,
	  pendingpovar,
	  pendingpoend_customer,
	  quantity,
	  quote_manufacturer,
	  quote_distributor,
	  quote_var,
	  renewal_status ,
	  status
	)
	SELECT
	  dw.id,
	  cat.base_price,
	  dw.customer,
	  dw.deployer,
	  dw.owner_org,
	  dw.manufacturer,
	  cust.org_name,
	  var.org_name,
	  dist.org_name,
	  manf.org_name,
	  cust.parent_org,
	  var.parent_org,
	  dist.parent_org,
	  manf.parent_org,
	  dw.expiry_date,
	  dw.pendingpofrom_dis,
	  dw.pendingpo,
	  dw.pendingpofrom_end_cust,
	  dw.quantity,
	  dw.quotefrom_manufacturer,
	  dw.quotefrom_distributor,
	  dw.quotefrom_var,
	  dw.renewal_status,
	  dw.status
	 FROM
	  deployment_workflow as dw, catalog_product as cat, organization as manf, organization as dist, organization as var, organization as cust
	 WHERE
	  dw.catalog_product = cat.catalog_id AND
	  dw.manufacturer = manf.org_id AND dw.owner_org = dist.org_id AND dw.deployer = var.org_id AND dw.customer = cust.org_id
	  AND
	  expiry_date BETWEEN start_date AND end_date;


    /* STEP 3: Handle population for null customers */
	INSERT INTO deployment_metrics (
	  deployment_id,
	  catalog_base_price,
	  customer,
	  deployer,
	  distributor,
	  manufacturer,
	  customer_name,
	  deployer_name,
	  distributor_name,
	  manufacturer_name,
	  parent_customer_name,
	  parent_deployer_name,
	  parent_distributor_name,
	  parent_manufacturer_name,
	  expiry,
	  pendingpodistributor,
	  pendingpovar,
	  pendingpoend_customer,
	  quantity,
	  quote_manufacturer,
	  quote_distributor,
	  quote_var,
	  renewal_status ,
	  status
	)
	SELECT
	  dw.id,
	  cat.base_price,
	  dw.customer,
	  dw.deployer,
	  dw.owner_org,
	  dw.manufacturer,
	  null,
	  var.org_name,
	  dist.org_name,
	  manf.org_name,
	  null,
	  var.parent_org,
	  dist.parent_org,
	  manf.parent_org,
	  dw.expiry_date,
	  dw.pendingpofrom_dis,
	  dw.pendingpo,
	  dw.pendingpofrom_end_cust,
	  dw.quantity,
	  dw.quotefrom_manufacturer,
	  dw.quotefrom_distributor,
	  dw.quotefrom_var,
	  dw.renewal_status,
	  dw.status
	 FROM
	  deployment_workflow as dw, catalog_product as cat, organization as manf, organization as dist, organization as var
	 WHERE
	  dw.customer is null AND
	  dw.catalog_product = cat.catalog_id AND
	  dw.manufacturer = manf.org_id AND dw.owner_org = dist.org_id AND dw.deployer = var.org_id
	  AND expiry_date BETWEEN start_date AND end_date;

    /* STEP 4: Update the quoted days in advance value for manufacturers */
	UPDATE deployment_metrics as dm
	SET quoted_days_in_advance_manufacturer = extract(day from (''1970-01-01 00:00:00 GMT''::timestamp + ((dm.expiry/1000)::text)::interval) - (''1970-01-01 00:00:00 GMT''::timestamp + ((qd.time/1000)::text)::interval) )
	FROM quote_detail qd
	WHERE dm.quote_manufacturer IS NOT NULL
	AND dm.quote_manufacturer = qd.id;
	/* STEP 5: Update the quoted days in advance value for distributers */
	UPDATE deployment_metrics as dm
	SET quoted_days_in_advance_distributor = extract(day from (''1970-01-01 00:00:00 GMT''::timestamp + ((dm.expiry/1000)::text)::interval) - (''1970-01-01 00:00:00 GMT''::timestamp + ((qd.time/1000)::text)::interval) )
	FROM quote_detail qd
	WHERE dm.quote_distributor IS NOT NULL
	AND dm.quote_distributor = qd.id;
	/* STEP 6: Update the quoted days in advance value for vars */
	UPDATE deployment_metrics as dm
	SET quoted_days_in_advance_var = extract(day from (''1970-01-01 00:00:00 GMT''::timestamp + ((dm.expiry/1000)::text)::interval) - (''1970-01-01 00:00:00 GMT''::timestamp + ((qd.time/1000)::text)::interval) )
	FROM quote_detail qd
	WHERE dm.quote_var IS NOT NULL
	AND dm.quote_var = qd.id;
	/* STEP 7: Update the quoted days in advance value with max value */
	UPDATE deployment_metrics SET quoted_days_in_advance = GREATEST(
		quoted_days_in_advance_manufacturer,
		quoted_days_in_advance_distributor,
		quoted_days_in_advance_var
	);
	/* STEP 7: Just return true for Hibernate to handle it */
    RETURN TRUE;
    END;
' LANGUAGE plpgsql;