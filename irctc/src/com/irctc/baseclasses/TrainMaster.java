package com.irctc.baseclasses;

public class TrainMaster
{   
	private int train_master_id;
	private String train_master_no;
	private String train_master_name;
	private int train_master_source_station;
	private int train_master_destination_station;
	private double train_master_distance;
	private String train_master_departure_time;
	private String train_master_arrival_time;
	private String train_master_travelling_time;
	private String train_master_remarks;
	private String train_master_flag;
	
	public int getTrain_master_id()
	{
		return train_master_id;
	}

	public void setTrain_master_id(int train_master_id)
	{
		this.train_master_id=train_master_id;
	}

	public String getTrain_master_no()
	{
		return train_master_no;
	}

	public void setTrain_master_no(String train_master_no)
	{
		this.train_master_no=train_master_no;
	}

	public String getTrain_master_name()
	{
		return train_master_name;
	}

	public void setTrain_master_name(String train_master_name)
	{
		this.train_master_name=train_master_name;
	}

	public int getTrain_master_source_station()
	{
		return train_master_source_station;
	}

	public void setTrain_master_source_station(int train_master_source_station)
	{
		this.train_master_source_station=train_master_source_station;
	}

	public int getTrain_master_destination_station()
	{
		return train_master_destination_station;
	}

	public void setTrain_master_destination_station(int train_master_destination_station)
	{
		this.train_master_destination_station=train_master_destination_station;
	}

	public double getTrain_master_distance()
	{
		return train_master_distance;
	}

	public void setTrain_master_distance(double train_master_distance)
	{
		this.train_master_distance=train_master_distance;
	}

	public String getTrain_master_departure_time()
	{
		return train_master_departure_time;
	}

	public void setTrain_master_departure_time(String train_master_departure_time)
	{
		this.train_master_departure_time=train_master_departure_time;
	}

	public String getTrain_master_arrival_time()
	{
		return train_master_arrival_time;
	}

	public void setTrain_master_arrival_time(String train_master_arrival_time)
	{
		this.train_master_arrival_time=train_master_arrival_time;
	}

	public String getTrain_master_travelling_time()
	{
		return train_master_travelling_time;
	}

	public void setTrain_master_travelling_time(String train_master_travelling_time)
	{
		this.train_master_travelling_time=train_master_travelling_time;
	}

	public String getTrain_master_remarks()
	{
		return train_master_remarks;
	}

	public void setTrain_master_remarks(String train_master_remarks)
	{
		this.train_master_remarks=train_master_remarks;
	}

	public String getTrain_master_flag()
	{
		return train_master_flag;
	}

	public void setTrain_master_flag(String train_master_flag)
	{
		this.train_master_flag=train_master_flag;
	}
}