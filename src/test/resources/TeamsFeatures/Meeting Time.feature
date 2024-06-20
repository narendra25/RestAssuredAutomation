@MeetingTime 

Feature: Testing MeetingTime Api in Teams module 

Scenario Outline: validate MeetingTime  

Given add the MeetingTime payload with "<userPrincipalName>" "<startDate>" "<endDate>" 

When user send Post request with MeetingTime api 

Then Validate response body of MeetingTime 

And validate response headers of the MeetingTime 

Examples: 

| userPrincipalName | startDate | endDate | 

| siva.yannam@ojas-it.com | 2023-07-01 | 2023-07-31 | 