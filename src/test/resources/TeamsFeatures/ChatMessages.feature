@ChatMessage 

Feature: Testing ChatMessage Api in Teams module 

Scenario Outline: validate ChatMessage  

Given add the ChatMessage payload with "<userPrincipalName>" "<startDate>" "<endDate>" 

When user send Post request with ChatMessage api 

Then Validate response body of ChatMessage 

And validate response headers of the ChatMessage 

Examples: 

| userPrincipalName | startDate | endDate | 

| siva.yannam@ojas-it.com | 2023-07-01 | 2023-07-31 |