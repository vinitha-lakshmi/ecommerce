package com.estee.lauder;

public enum PageObjects {
	
	ButtonClose("button#cboxClose"),
	SearchIcon("span.page-utilities__search-icon"),
	SearchInput("input#search"),
	ProductList("div.typeahead-product-results > div.product-result div.product-result__name > a"),
	ProductQuantity("a.product-full__quantity"),
	ProductQuantityList("ul.quantity-selectBox-dropdown-menu > li > a"),
	AddToBag(".//button[text()='Add to Bag' and contains(@class,'product-full__add-button')]"),
	CartCount("div.utility-nav__cart-count"),
	CartIcon("span.utility-nav__cart-icon"),
	ProductRemove("a[data-test-id='cart_product_remove']"),
	CartEmpty("div[id='error_cart.empty']"),
	ItemCount("span.item-count__number"),
	SignInIcon("span.user-login-state"),
	SignInEmail("input#sign-in-component__EMAIL_ADDRESS"),
	SignInPassword("input[data-test-id='gnav_login_form_password']"),
	SignInButton("input[value='Sign In']"),
	SignInError("ul.error_messages > li")
	;	
	
	private String property;

	private PageObjects(String property) {
		this.setProperty(property);
	}

	private void setProperty(String property) {
		this.property = property;
	}

	public String getProperty() {
		return property;
	}

}
