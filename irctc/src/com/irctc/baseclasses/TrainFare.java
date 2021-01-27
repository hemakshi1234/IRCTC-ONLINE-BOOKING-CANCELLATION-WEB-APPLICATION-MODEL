package com.irctc.baseclasses;

public class TrainFare{
	private int train_fare_id;
	private int train_master_id;
	private double train_fare_sleeper;
	private double train_fare_sleeper_totalseats;
	private double train_fare_ac1;
	private double train_fare_ac1_totalseats;
	private double train_fare_ac2;
	private double train_fare_ac2_totalseats;
	private double train_fare_ac3;
	private double train_fare_ac3_totalseats;
	private String train_fare_remarks;
	private String train_fare_flag;

	
	public int getTrain_fare_id(){
		return train_fare_id;
	}

	public void setTrain_fare_id(int train_fare_id){
		this.train_fare_id=train_fare_id;
	}

	
	public int getTrain_master_id(){
		return train_master_id;
	}

	public void setTrain_master_id(int train_master_id){
		this.train_master_id=train_master_id;
	}

	
	public double getTrain_fare_sleeper(){
		return train_fare_sleeper;
	}

	public void setTrain_fare_sleeper(double train_fare_sleeper){
		this.train_fare_sleeper=train_fare_sleeper;
	}

	public double getTrain_fare_sleeper_totalseats(){
		return train_fare_sleeper_totalseats;
	}

	public void setTrain_fare_sleeper_totalseats(double train_fare_sleeper_totalseats){
		this.train_fare_sleeper_totalseats=train_fare_sleeper_totalseats;
	}

	public double getTrain_fare_ac1(){
		return train_fare_ac1;
	}

	public void setTrain_fare_ac1(double train_fare_ac1){
		this.train_fare_ac1=train_fare_ac1;
	}

	public double getTrain_fare_ac1_totalseats(){
		return train_fare_ac1_totalseats;
	}

	public void setTrain_fare_ac1_totalseats(double train_fare_ac1_totalseats){
		this.train_fare_ac1_totalseats=train_fare_ac1_totalseats;
	}

	public double getTrain_fare_ac2(){
		return train_fare_ac2;
	}

	public void setTrain_fare_ac2(double train_fare_ac2){
		this.train_fare_ac2=train_fare_ac2;
	}

	public double getTrain_fare_ac2_totalseats(){
		return train_fare_ac2_totalseats;
	}

	public void setTrain_fare_ac2_totalseats(double train_fare_ac2_totalseats){
		this.train_fare_ac2_totalseats=train_fare_ac2_totalseats;
	}

	public double getTrain_fare_ac3(){
		return train_fare_ac3;
	}

	public void setTrain_fare_ac3(double train_fare_ac3){
		this.train_fare_ac3=train_fare_ac3;
	}

	public double getTrain_fare_ac3_totalseats(){
		return train_fare_ac3_totalseats;
	}

	public void setTrain_fare_ac3_totalseats(double train_fare_ac3_totalseats){
		this.train_fare_ac3_totalseats=train_fare_ac3_totalseats;
	}

	public String getTrain_fare_remarks(){
		return train_fare_remarks;
	}

	public void setTrain_fare_remarks(String train_fare_remarks){
		this.train_fare_remarks=train_fare_remarks;
	}

	public String getTrain_fare_flag(){
		return train_fare_flag;
	}

	public void setTrain_fare_flag(String train_fare_flag){
		this.train_fare_flag=train_fare_flag;
	}
}