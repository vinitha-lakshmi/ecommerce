Feature: E-Commerce Sample Test

@SHOPPING
Scenario Outline: Verify items can be added to shopping cart
	Given page is opened successfully
	When I search for "<ProductName>"
	Then I select the product "<ItemName>"
	And I select the quantity as "<QuantityCount>"
	And I verify "<ItemCount>" items are added to the cart
	When I clear the cart
	Then I verify cart gets empty

	Examples:
	 	|ProductName			|ItemName								|QuantityCount	|ItemCount	|
	 	|advanced night repair	|concentrated-recovery-powerfoil-mask	|2				|2			|
	
@SIGNIN
Scenario Outline: Login Validation
	Given page is opened successfully
	When I sign in with "<UserName>" and "<Password>"
	Then I verify sign in is unsuccessful "<ExpectedMessage>"

		Examples:
	 	|UserName			|Password	|ExpectedMessage																	|
	 	|vinitha@gmail.com	|test123	|We do not have an account associated with that email address. Please register now.	|
	 	|vinitha			|test123	|Please enter your email address in the following format: jane@aol.com				|
	 	|vinitha@gmail.com	|			|Please enter your password.														|
	 	|					|test123	|Please enter your email address.													|
	 	
