package setting;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;

import org.jetbrains.annotations.Nls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.annotation.Nullable;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.CodeType;
import utils.Utils;

import static utils.ConstantKt.Default_Language_Java;
import static utils.ConstantKt.Default_Language_Key;
import static utils.ConstantKt.Default_Language_Kotlin;

/**
 * @author deadline
 * @time 2018/6/23
 *
 * 全局的关于该插件的设置
 * Preferences -> Other Settings -> Design Pattern
 */
public class DesignPatternSettings implements Configurable {

    private JPanel mPanel;
    private JRadioButton Java;
    private JRadioButton Kotlin;


    @Nls
    @Override
    public String getDisplayName() {
        return "Design Pattern";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        reset();
        return mPanel;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        int value = Java.isSelected() ? Default_Language_Java : Default_Language_Kotlin;
        PropertiesComponent.getInstance().setValue(Default_Language_Key, value, Default_Language_Java);
    }

    @Override
    public void reset() {
        CodeType codeType = Utils.INSTANCE.getDefaultCodeType();
        Java.setSelected(codeType == CodeType.Java);
        Kotlin.setSelected(codeType == CodeType.Kotlin);
    }

    @Override
    public void disposeUIResources() {

    }

}
