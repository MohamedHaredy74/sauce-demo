package utils;

import org.testng.IExecutionListener;

public class ExcecutionListener  implements IExecutionListener {


    @Override
    public void onExecutionStart() {
        LogUtils.info("Execution started");
    }

    @Override
    public void onExecutionFinish() {
        LogUtils.info("Execution finished");
    }
}
