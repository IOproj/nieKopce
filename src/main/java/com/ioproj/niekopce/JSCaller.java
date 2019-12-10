package com.ioproj.niekopce;


import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.springframework.stereotype.Component;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

@Component
public class JSCaller {

    public void activateJS() {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine engine = scriptEngineManager.getEngineByName("nashorn");
        try {
            URL url = Resources.getResource("static/js/custom.js");
            String text = Resources.toString(url, Charsets.UTF_8);
            engine.eval("print('welcome to javascript execution using java')");
            engine.eval(text);
            Invocable invocable = (Invocable)engine;
            invocable.invokeFunction("loginBtnFunction");
        } catch (ScriptException | NoSuchMethodException | IOException e) {
            e.printStackTrace();
        }
    }
}
