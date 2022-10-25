 

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/* **********************************************************
 * Programmer:	Rob Sveinson
 * Class:		CS20S
 * 
 * Assignment:	hospitbal mvc gui devolopment project
 *
 * Description:	controller for hospital bill calculator app
 *                                      is aware of both the view and the model
 *                                      viewis aware of this, model is aware of this
 * *************************************************************
 */
 
 // import files here as needed
 
 
 public class Controller
 {  // begin class
 	
    // *********** class constants **********

    // ********** instance variable **********
     
     private ArrayList<Patient> patientList = new ArrayList();
     private Patient p;             // pointer a patient object
     private PatientGUI ui;     // pointer to a gui object

    // ********** constructors ***********
     
     public Controller(){
         System.out.println("controller being created...");
     } // end constructor

     protected void addUI(PatientGUI gui){
         this.ui = gui;
     } // end addUI
     
     public void submitButtonClicked(String f, String l, 
                                                int d, char r, char n, char ph, char t){
         System.out.println("submit button clicked this is in the controller");
         p = new Patient(f, l, d, r, n, ph, t);
         patientList.add(p);
         ui.patientListModel.addElement(p);
         
     } // end submit Button Clicked
     
     protected String getPatient(int i){
         p = patientList.get(i);
         //System.out.println("pathint: " + p.toString());
         
         // build an output string with all of the patient's details
        String detail = p.getId() + "\n";
        detail += p.getRoomCost() + "\n";
        detail += p.getNurseCost() + "\n";
        detail += p.getPhoneCost() + "\n";
        detail += p.getTVCost() + "\n";
        detail += p.getTotalBill() + "\n";
        System.out.println(detail);
        
        return detail;
     } // end get Patient
     
     protected void writePatientList(){
        try{
            FileOutputStream fos = new FileOutputStream("patients.tmp");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(patientList);      // write the entire arraylist of patients to file
            oos.close();
        } // end try
        catch(IOException ex){
            
        } // end catch io exception         
     } // end write patient list
     
     protected void getPatientList(){
         try{
             FileInputStream fis = new FileInputStream("patients.tmp");
             ObjectInputStream ois = new ObjectInputStream(fis);
             
             try{
                 patientList = (ArrayList<Patient>)ois.readObject();
             } // end try
             catch(ClassNotFoundException e){
                 
             } // end catch ClasNotFountException
         } // end try
         catch(IOException ex){
             
         } // end catch io exception
         
         // the array list has been retrieved but it is not in the 
         // list box listModel
         
         ui.loadPatientListModel(patientList);
     } // end getPatientList
     
     protected void loadListButtonClicked(Patient p){
         int nid = p.getId();
         nid++;
         p.setNextId(nid);
     } // end loadListButtonCicked
 
 }  // end class