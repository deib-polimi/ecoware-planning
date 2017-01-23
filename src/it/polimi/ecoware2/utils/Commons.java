package it.polimi.ecoware2.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import it.polimi.ecoware2.executor.Allocation;


public class Commons
{
	public static final String MONITORING_KEY = "___mon___";
	public static final String PLAN_KEY = "___plan___";
	public static final String PLAN_UNAPPROX_KEY = "___unapprox_plan___";
	public static final String ANALYSIS_KEY = "__analysis__";

	public static final String RT_KEY = "__rt__";
	public static final String REQ_KEY = "__req__";
	public static final String CURRENT_ALLOCATION_KEY = "___ca___";
	public static float CONTROLLER_ALPHA;
	public static float CONTROLLER_ALPHA_PWITTER;
	public static float CONTROLLER_ALPHA_RUBIS;

	
	public static String CONTAINER_HOST;// = "131.175.135.184";
	public static int CONTAINER_PORT;// = 2375;
	public static  String SERVER_HOST; //= "pwitter-lb-1145964028.us-west-2.elb.amazonaws.com";
	public static  int SERVER_PORT;// = 80;
	public static  String CONTAINER_ID;// = "0646d0cd73155d57cff79533dfb662d32c06ed881eec898bf8e01198b6a0ce76";
	
	public static  Allocation MAX_ALLOCATION;//=new Allocation((long) (10*1E9), 10);
	public static  Allocation MIN_ALLOCATION;//=new Allocation((long) (1*1E9), 1);

	public static  float SLA; //=0.5f;
	public static  int SAMPLE_TIME;// = 30;

	public static  String EXECUTOR_EXECUTE_ENDPOINT;// = "http://localhost:8000/api/executor";
	public static  String EXECUTOR_ALLOCATION_ENDPOINT;

	public static  String SERVER_PATH;// = "pweets"; //"/rubis/servlet/BrowseRegions";
	
	public static  String AWS_SCALE_GROUP;// = "pwitter-web";
	
	public static  String AWS_ACCESS_KEY;
	
	public static  String AWS_SECRET_KEY;

	public static  String AWS_REGION;

	public static  String TEST_NAME;

	public static  int STEP_DURATION;
	
	public static  int CONTROL_PERIOD;
	
	public static  List<Integer> STEPS;

	static {
		
		try {
			File file = new File("ecoware.properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();

			Enumeration<?> enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				System.out.println(key + ": " + value);
			}
			
			CONTAINER_HOST = properties.getProperty("CONTAINER_HOST");
			CONTAINER_PORT = Integer.parseInt(properties.getProperty("CONTAINER_PORT"));
			SERVER_HOST = properties.getProperty("SERVER_HOST");
			SERVER_PORT = Integer.parseInt(properties.getProperty("SERVER_PORT"));
			MAX_ALLOCATION = new Allocation((long) (Integer.parseInt(properties.getProperty("MAX_ALLOCATION_MEM"))*1E9), Integer.parseInt(properties.getProperty("MAX_ALLOCATION_CORE")));
			MIN_ALLOCATION = new Allocation((long) (Integer.parseInt(properties.getProperty("MIN_ALLOCATION_MEM"))*1E9), Integer.parseInt(properties.getProperty("MIN_ALLOCATION_CORE")));
			SLA = Float.parseFloat(properties.getProperty("SLA"));
			SAMPLE_TIME = Integer.parseInt(properties.getProperty("SAMPLE_TIME"));
			EXECUTOR_EXECUTE_ENDPOINT = properties.getProperty("EXECUTOR_EXECUTE_ENDPOINT");
			EXECUTOR_ALLOCATION_ENDPOINT = properties.getProperty("EXECUTOR_ALLOCATION_ENDPOINT");
			SERVER_PATH = properties.getProperty("SERVER_PATH");
			AWS_SCALE_GROUP = properties.getProperty("AWS_SCALE_GROUP");
			// CONTROLLER_ALPHA_RUBIS = Float.parseFloat(properties.getProperty("CONTROLLER_ALPHA_RUBIS"));
			// CONTROLLER_ALPHA_PWITTER = Float.parseFloat(properties.getProperty("CONTROLLER_ALPHA_PWITTER"));
			CONTROLLER_ALPHA = Float.parseFloat(properties.getProperty("CONTROLLER_ALPHA"));
			AWS_ACCESS_KEY = properties.getProperty("AWS_ACCESS_KEY");
			AWS_SECRET_KEY = properties.getProperty("AWS_SECRET_KEY");
			AWS_REGION = properties.getProperty("AWS_REGION");
			TEST_NAME = properties.getProperty("TEST_NAME");
			STEP_DURATION = Integer.parseInt(properties.getProperty("STEP_DURATION"));
			CONTROL_PERIOD = Integer.parseInt(properties.getProperty("CONTROL_PERIOD"));
			STEPS = Arrays.asList(properties.getProperty("STEPS").split(",")).stream().map(a -> Integer.parseInt(a)).collect(Collectors.toList());
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}