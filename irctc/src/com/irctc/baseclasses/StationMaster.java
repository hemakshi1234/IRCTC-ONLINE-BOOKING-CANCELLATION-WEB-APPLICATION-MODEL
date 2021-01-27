package com.irctc.baseclasses;

public class StationMaster
{
    private int stationid;
	private String stationcode;
	private String stationname;
	private String stationremarks;
	private String stationflag;
	
	public int getStationMasterId()
	{
		return stationid;
	}

	public void setStationMasterId( int si)
	{
		stationid=si;
	}


	public String getStationMasterCode()
	{
		return stationcode;
	}

	public void setStationMasterCode(String sc)
	{
		stationcode=sc;
	}

	public String getStationMasterName()
	{
		return stationname;
	}

	public void setStationMasterName(String sn)
	{
		stationname=sn;
	}
	

	public String getStationMasterRemarks()
	{
		return stationremarks;
	}

	public void setStationMasterRemarks(String sr)
	{
		this.stationremarks=sr;
	}

	public String getStationMasterFlag()
	{
		return stationflag;
	}

	public void setStationMasterFlag(String sf)
	{
		stationflag=sf;
	}

}
