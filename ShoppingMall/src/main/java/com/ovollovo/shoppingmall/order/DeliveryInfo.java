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
		return "�ֹ��� : " + buyer + ", ������ : " + shippingRecipient + ", �����ȣ : " + zipCode
				+ ", �ּ� : " + address + ", ���ּ� : " + detailAddress + ", ���� �׸� : " + reference;
	}
}
