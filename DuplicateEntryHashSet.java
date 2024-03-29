import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class DuplicateEntryHashSet {

    public static void main(String[] args){

        //MergeUserDirectory problem
        
        HashSet<String> u1EmailList = new HashSet<>();
        u1EmailList.add("j1@j1.com");
        u1EmailList.add("j2@j2.com");
        u1EmailList.add("j3@j3.com");
        UserRecord u1 = new UserRecord("john", u1EmailList);

        HashSet<String> u2EmailList = new HashSet<>();
        u2EmailList.add("j4@j4.com");
        u2EmailList.add("j2@j2.com");
        UserRecord u2 = new UserRecord("john", u2EmailList);

        HashSet<String> u3EmailList = new HashSet<>();
        u3EmailList.add("m1@m1.com");
        u3EmailList.add("m2@m2.com");
        UserRecord u3 = new UserRecord("marry", u3EmailList);

        HashSet<String> u4EmailList = new HashSet<>();
        u4EmailList.add("j5@j5.com");
        u4EmailList.add("j6@j6.com");
        UserRecord u4 = new UserRecord("john", u4EmailList);

        HashSet<String> u5EmailList = new HashSet<>();
        u5EmailList.add("j4@j4.com");
        u5EmailList.add("j3@j3.com");
        UserRecord u5 = new UserRecord("john", u5EmailList);

        List<UserRecord> userRecords = Arrays.asList(u1, u2, u3, u4, u5);

        HashSet<UserRecord> mergedRecords = mergeUserRecords(userRecords);
        System.out.println("merged records: "+ mergedRecords);
        boolean doesExist = mergedRecords.contains(u4);
        System.out.println("doesExist: "+doesExist);
        doesExist = mergedRecords.contains(u5);
        System.out.println("doesExist: "+doesExist);
    }

    public static HashSet<UserRecord> mergeUserRecords(List<UserRecord> records) {

        HashSet<UserRecord> set = new HashSet<>();
        records.forEach(record -> {
            set.add(record);
        });
        return set;
    }
}

class UserRecord {

    private String userName;
    private HashSet<String> emailList;

    public UserRecord(String userName, HashSet<String> emailList){
        this.userName = userName;
        this.emailList = emailList;
    }

    @Override
    public boolean equals(Object obj) {

        /*
        1. check whether the provided input is null
        2. check whether both the objects are of the same type or not
        */
        if(null == obj || this.getClass() != obj.getClass()) {
            return false;
        }

        //check if both the objects are referring to the same object
        if(this == obj) {
            return true;
        }

        /*
        As per this use-case, if there is any common email among two user records,
        we will merge both the records and both the records will be treated equal, else treat both records as different
         */
        UserRecord user2 = (UserRecord) obj;
        if(null != user2.getEmailList() && !user2.getEmailList().isEmpty()
            && null != this.emailList && !this.emailList.isEmpty()) {

            HashSet<String> user2EmailList = user2.getEmailList();
            for(String email : this.emailList) {

                if(user2EmailList.contains(email)){

                    //merge email lists of both the records, if common email is found
                    /////System.out.println("curent object: "+this);
                    /////System.out.println("object to be compared with: "+user2);
                    user2EmailList.addAll(this.emailList);
                    //this.emailList.clear();
                    this.emailList.addAll(user2EmailList);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public int hashCode() {

        int hash = 0;
        for(char c : userName.toCharArray()){
            hash = hash + c*c;
        }
        ////System.out.println("hashCode returned: "+hash+" - obj: "+this);
        return hash;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public HashSet<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(HashSet<String> emailList) {
        this.emailList = emailList;
    }

    @Override
    public String toString() {

        return userName + " -> " + emailList;
    }
}
