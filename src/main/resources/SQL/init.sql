-- Create first organization
INSERT INTO ORGANIZATION(
 org_id, address, allow_autocheckout,
 city, email, org_name,
 org_type, parent_org, phone,
 remarks, state, status,
 status_date, territory, zip
)
SELECT
  1, '2528 Qume Drive, Suite 2', FALSE,
  'San Jose', '', 'ZAutomate',
  'Clearance', '', '408-908-0284',
  '', 'CA', 'active',
  1401580800, '', '95131'
WHERE NOT EXISTS (SELECT * FROM ORGANIZATION);


-- Create first user
INSERT INTO PERSON(
 username, activation_code, address,
 city, comments, device_id,
 email, emailnotification, force_pswd_change,
 frequency, frequency_specification, fullname,
 is_default, mail_verification_status, notification_status,
 password, phone, role,
 state, status, status_date,
 third_party_name, zip, organization
)
SELECT
  'admin', '', '2528 Qume Drive, Suite 2',
  'San Jose', '', '',
  'veeraiyan@zautomate.net', '','',
  '', '', 'Z-Admin User',
  '', '', 'Disabled',
  '$2a$10$HS319US29NzArHqD21gT8ei6BGAiEk.tUKEaJMs3FHAQN/wKDfTaW', '408-908-0284', 'z-admin',
  'CA', 'Active', 1401580800,
  '', '95131', 1
WHERE NOT EXISTS (SELECT * FROM PERSON);


