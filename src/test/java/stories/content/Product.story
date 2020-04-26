


Scenario: Product should provide valid data
Given a Product
When create name with invalid <value>
Then exception should be thrown

Examples:
|value|
||
|null|
| |
|      |




