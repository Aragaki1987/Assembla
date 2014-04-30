package lab1.scale;

import lab1.adapter.BuildAuto;
import lab1.adapter.ProxyAutomobile;
import lab1.exception.AutomotiveException;
import lab1.model.Automotive;

public class EditOptions extends BuildAuto {
    private boolean available = false;

    @Override
    public synchronized void printAuto() {
        while (!available) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        available = false;
        notifyAll();
        super.printAuto();
    }

    @Override
    public synchronized void updateOptionSetName(String autoName, String optionSetName, String newName) throws AutomotiveException {
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        available = true;
        notifyAll();
        super.updateOptionSetName(autoName, optionSetName, newName);
    }

    @Override
    public synchronized void updateOptionPrice(String autoName, String optionSetName, String optionName, int newprice) throws AutomotiveException {
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        available = true;
        notifyAll();
        super.updateOptionPrice(autoName, optionSetName, optionName, newprice);
    }

    @Override
    public synchronized Automotive getAutoMotive(String name) {
        while (!available) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        available = false;
        return super.getAutoMotive(name);
    }

    @Override
    public synchronized void setAutoMotive(Automotive auto) {
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        available = true;
        notifyAll();
        super.setAutoMotive(auto);
    }

    @Override
    public synchronized void deleteAutoMotive(String name) {
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        available = true;
        notifyAll();
        super.deleteAutoMotive(name);
    }

}
