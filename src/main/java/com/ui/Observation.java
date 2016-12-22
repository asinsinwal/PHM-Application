package com.ui;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.database.ConnectionManager;
import com.database.InsertQueries;
import com.database.SelectQueries;
import com.exception.PhmException;
import com.model.ObservationDTO;
import com.model.PersonDTO;
import com.model.RecommendationDTO;
import com.model.RecordDiseaseDTO;
import com.util.StringsUtil;

/*
 * @author Animesh
 * 
 */
public class Observation {
	
	public static void showScreen(PersonDTO patient) {
		// TODO Auto-generated method stub
		try{
			
			Scanner sc = new Scanner(System.in);
			boolean flag = true;
			int input=-1;
			
			while (flag) {
				sc = new Scanner(System.in);
				System.out.println(StringsUtil.LOGIN_MESSAGE);
				System.out.println("Select an option ");
				System.out.println("1. View Observation");
				System.out.println("2. Enter Observation");
				System.out.println("3. Go Back");
			
				input = Integer.valueOf(sc.nextLine());	
				
				switch (input) 
				{
					case 1:
						viewObservation(patient);
						break;
					case 2:
						addObservation(patient);
						break;
					case 3:
						flag = false;
						break;
					default:
						System.out.println("Invalid option. Try again.!");			
						break;
					}
				}
			}catch (Exception pe){
				System.out.println("ERROR" + pe.getMessage());
				pe.printStackTrace();
			}		
	}

	public static void viewObservation(PersonDTO patient) throws PhmException, SQLException {
		// TODO Auto-generated method stub
		Connection con = new ConnectionManager().getConnection();
		List<ObservationDTO> observationDTOs = SelectQueries.getPersonObservations(con, patient.getPersonId());
		int count=0,input;
		String observation_type = null;
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		if( null != observationDTOs)
		{
			for(ObservationDTO obs : observationDTOs) {
				System.out.println("\nObservation No. " + ++count);
				System.out.print("Observation Value: " + obs.getObservationValue() + "\t");
				observation_type = SelectQueries.getObservationType(con, obs.getObservationId());
				System.out.print("Observation Type: " + observation_type + "\t");
				System.out.println();
				System.out.print("Observation Time: " + obs.getObservationTime() + "\t");
				System.out.print("Recorded Time: " + obs.getRecordTime());
				System.out.println();
			}
		}
		else
		{
			System.out.println("No Observation found.");
		}
		
		System.out.println("1. Go Back.");
		while(flag)
		{
			System.out.println("");
			input = Integer.valueOf(sc.nextLine());
			
			if(input == 1)
				flag = false;
			else
				System.out.println("\nType '1' to Go Back");
		}
		con.close();
	}

	public static void addObservation(PersonDTO patient) throws PhmException, SQLException, ParseException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String input = null;
		Connection con = new ConnectionManager().getConnection();
		List<RecordDiseaseDTO> recordDiseaseDTOs = SelectQueries.getPersonDiseases(con, patient.getPersonId());

		List<RecommendationDTO> recommendation_list = new ArrayList<RecommendationDTO>();
		HashSet<String> hs = new HashSet<String>();
		

		List<RecommendationDTO>recomDTO2s = SelectQueries.getRecommendationByPId(con, patient.getPersonId());
		
		for(RecommendationDTO record:recomDTO2s){
			if(!hs.add(record.getDescription()))
			{
				
			}
			else{
				hs.add(record.getDescription());
				recommendation_list.add(record);
			}
		}
		for(RecordDiseaseDTO record2: recordDiseaseDTOs)
		{
			List<RecommendationDTO>recomDTO1s = SelectQueries.getRecommendationByDId(con, record2.getDiseaseId());
			
			for(RecommendationDTO record1 :recomDTO1s){
				if(!hs.add(record1.getDescription())){
				}
				else{
					hs.add(record1.getDescription());
					recommendation_list.add(record1);
				}
			}
		}
		
		System.out.println("Enter Observation Type, for entering a new Observation");
		for(RecommendationDTO record: recommendation_list)
		{
			System.out.println(record.getRecommendationId() + ". " + record.getDescription());		
		}
		
		System.out.println("0. Go Back.");
		
		int recommendation_no = Integer.valueOf(sc.nextLine());
		
		if(recommendation_no == 0)
		{
			con.close();
			return;
		}
		else
		{
			System.out.println("Enter Observation Value: ");
			String obv_value = sc.nextLine();
			System.out.println("Enter Observation Time:  (MM/dd/yyyy)");
			String ob_time = sc.nextLine();
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			Date date = formatter.parse(ob_time);
			java.sql.Timestamp obv_time = new Timestamp(date.getTime());
			 
			ObservationDTO observation = new ObservationDTO(-1, patient.getPersonId(), recommendation_no, obv_value, null, obv_time);
			boolean status = InsertQueries.insertObservation(con, observation);
			if (status) {
				System.out.println("Observation added Successfully");
			} else {
				// TODO: implement error handling logic here.
				System.out.println("Failed to add an observation");
			}
		}
		con.close();
	}
}
