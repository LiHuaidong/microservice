package hdli.behavioural.observer;

import java.util.List;

public class TeacherSubject implements Subject {

    private List<Observer> observerList;

    @Override
    public void addObserver() {

    }

    @Override
    public void deleteObserver() {

    }

    @Override
    public void notifyObserver() {

        if(observerList != null && observerList.size() > 0 ) {
            for(Observer observer : observerList) {
                observer.update(this);
            }
        }

    }
}
