package hdli.behavioural.observer;

public class StudentObserver implements Observer {
    @Override
    public void update(Subject subject) {

        if(subject instanceof TeacherSubject) {
            System.out.println("teacher");
        }

        if(subject instanceof SchoolMasterSubject) {
            System.out.println("school master");
        }
    }
}
