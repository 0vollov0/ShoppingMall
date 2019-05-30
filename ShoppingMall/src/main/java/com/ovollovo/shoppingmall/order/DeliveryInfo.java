package com.ovollovo.shoppingmall.order;

public class DeliveryInfo {
	private String buyer;
	private String shippingRecipient;
	private String zipCode;
	private String address;
	private String detailAddress;
	private String reference;
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getShippingRecipient() {
		return shippingRecipient;
	}
	public void setShippingRecipient(String shippingRecipient) {
		this.shippingRecipient = shippingRecipient;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	@Override
	public String toString() {
		return "구매자 : " + buyer + ", 수령인 : " + shippingRecipient + ", 우편번호 : " + zipCode
				+ ", 주소 : " + address + ", 상세주소 : " + detailAddress + ", 참고 항목 : " + reference;
	}
	public int getEmptyField() {
		if (buyer.length() <= 1) {
			return 1;
		}
		if (shippingRecipient.length() <= 1) {
			return 2;
		}
		if (zipCode.length() <= 1) {
			return 3;
		}
		if (address.length() <= 1) {
			return 4;
		}
		if (detailAddress.length() <= 1) {
			return 5;
		}
		return 0;
	}
}
