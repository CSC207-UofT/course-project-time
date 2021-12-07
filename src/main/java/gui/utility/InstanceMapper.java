package gui.utility;

import gui.view.ViewModelBindingController;
import gui.viewmodel.ViewModel;

import java.util.HashMap;
import java.util.Map;

public class InstanceMapper {

    private final Map<Class<? extends ViewModelBindingController>, ViewModel> instanceMap = new HashMap<>();

    public ViewModel getViewModel(Class<? extends ViewModelBindingController> controller) {
        return instanceMap.get(controller);
    }

    public void addMapping(Class<? extends ViewModelBindingController> controller, ViewModel viewModel) {
        instanceMap.put(controller, viewModel);
    }
}
