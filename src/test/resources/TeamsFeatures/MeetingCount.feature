@MeetingCount 

Feature: Testing MeetingCount Api in Teams module 

Scenario Outline: validate MeetingCount  

Given add the MeetingCount payload with "<userPrincipalName>" "<startDate>" "<endDate>" 

When user send Post request with MeetingCount api 

Then Validate response body of MeetingCount 

And validate response headers of the MeetingCount 

Examples: 

| userPrincipalName | startDate | endDate | 

| siva.yannam@ojas-it.com | 2023-07-01 | 2023-07-31 |