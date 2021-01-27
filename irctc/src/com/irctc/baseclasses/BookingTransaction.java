package com.irctc.baseclasses;

public class BookingTransaction{
	private int booking_transaction_id;
	private int booking_master_id;
	private String booking_transaction_pass_name;
	private double booking_transaction_age;
	private String booking_transaction_food_pref;
	private String booking_transaction_berth_pref;
	private String booking_transaction_id_proof;
	private int booking_transaction_seat_allotment_id;
	private String booking_transaction_wait_list_no;
	private String booking_transaction_remarks;
	private String booking_transaction_flag;

	
	public int getBooking_transaction_id(){
		return booking_transaction_id;
	}

	public void setBooking_transaction_id(int booking_transaction_id){
		this.booking_transaction_id=booking_transaction_id;
	}
	
	public int getBooking_master_id(){
		return booking_master_id;
	}

	public void setBooking_master_id(int booking_master_id){
		this.booking_master_id=booking_master_id;
	}

	public String getBooking_transaction_pass_name(){
		return booking_transaction_pass_name;
	}

	public void setBooking_transaction_pass_name(String booking_transaction_pass_name){
		this.booking_transaction_pass_name=booking_transaction_pass_name;
	}

	public double getBooking_transaction_age(){
		return booking_transaction_age;
	}

	public void setBooking_transaction_age(double booking_transaction_age){
		this.booking_transaction_age=booking_transaction_age;
	}

	public String getBooking_transaction_food_pref(){
		return booking_transaction_food_pref;
	}

	public void setBooking_transaction_food_pref(String booking_transaction_food_pref){
		this.booking_transaction_food_pref=booking_transaction_food_pref;
	}

	public String getBooking_transaction_berth_pref(){
		return booking_transaction_berth_pref;
	}

	public void setBooking_transaction_berth_pref(String booking_transaction_berth_pref){
		this.booking_transaction_berth_pref=booking_transaction_berth_pref;
	}

	public String getBooking_transaction_id_proof(){
		return booking_transaction_id_proof;
	}

	public void setBooking_transaction_id_proof(String booking_transaction_id_proof){
		this.booking_transaction_id_proof=booking_transaction_id_proof;
	}

	public int getBooking_transaction_seat_allotment_id(){
		return booking_transaction_seat_allotment_id;
	}

	public void setBooking_transaction_seat_allotment_id(int booking_transaction_seat_allotment_id){
		this.booking_transaction_seat_allotment_id=booking_transaction_seat_allotment_id;
	}

	public String getBooking_transaction_wait_list_no(){
		return booking_transaction_wait_list_no;
	}

	public void setBooking_transaction_wait_list_no(String booking_transaction_wait_list_no){
		this.booking_transaction_wait_list_no=booking_transaction_wait_list_no;
	}

	public String getBooking_transaction_remarks(){
		return booking_transaction_remarks;
	}

	public void setBooking_transaction_remarks(String booking_transaction_remarks){
		this.booking_transaction_remarks=booking_transaction_remarks;
	}

	public String getBooking_transaction_flag(){
		return booking_transaction_flag;
	}

	public void setBooking_transaction_flag(String booking_transaction_flag){
		this.booking_transaction_flag=booking_transaction_flag;
	}
}