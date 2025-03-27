package viewmodels;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Base class for all ViewModels in the MVVM+C architecture.
 * ViewModels act as an intermediary between the Model and View,
 * handling the presentation logic and data transformations.
 */
public abstract class BaseViewModel {
    protected final PropertyChangeSupport propertyChangeSupport;
    
    public BaseViewModel() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }
    
    /**
     * Adds a property change listener to this ViewModel
     * @param listener PropertyChangeListener to add
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
    
    /**
     * Removes a property change listener from this ViewModel
     * @param listener PropertyChangeListener to remove
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
    
    /**
     * Notifies listeners that a property has changed
     * @param propertyName Name of the property
     * @param oldValue Old value of the property
     * @param newValue New value of the property
     */
    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
    
    /**
     * Initialize the ViewModel with data
     */
    public abstract void initialize();
    
    /**
     * Clean up resources used by this ViewModel
     */
    public abstract void cleanup();
} 