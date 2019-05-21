package com.centit.app.util;

import com.centit.sys.service.CodeRepositoryManager;

import org.directwebremoting.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InstantMsgImpl implements IInstantMsg {
    private static final String DEFAULT_SCRIPT = "jQuery.receive.receiveMessages";

    @Override
    public void push(List<String> userCodes, String message, String script) {
        sendMessage(getScriptSessionId(userCodes), message, script);
    }

    @Override
    public void push(List<String> userCodes, String message) {
        push(userCodes, message, DEFAULT_SCRIPT);
    }

    @Override
    public void pushAll(String message, String script) {
        sendMessageAll(message, script);

    }

    @Override
    public void pushAll(String message) {
        pushAll(message, DEFAULT_SCRIPT);

    }

    @SuppressWarnings("static-method")
	private void sendMessage(final List<String> scriptSessionIds, final String message, final String script) {
        Browser.withAllSessionsFiltered(new ScriptSessionFilter() {

            public boolean match(ScriptSession session) {
                return scriptSessionIds.contains(session.getId());
            }
        }, new Runnable() {
            @Override
            public void run() {
                ScriptBuffer sb = new ScriptBuffer();

                sb.appendCall(script, message);

                Collection<ScriptSession> sessions = Browser.getTargetSessions();

                for (ScriptSession scriptSession : sessions) {
                    scriptSession.addScript(sb);
                }
            }
        });
    }

    @SuppressWarnings("static-method")
	private void sendMessageAll(final String message, final String script) {
        Browser.withAllSessions(new Runnable() {
            @Override
            public void run() {
                ScriptSessions.addFunctionCall(script, message);
            }
        });
    }

    @SuppressWarnings("static-method")
	private List<String> getScriptSessionId(List<String> userCodes) {
        List<String> scriptSessionId = new ArrayList<String>();
        for (String userCode : userCodes) {
        	if(CodeRepositoryManager.USERCODE_SCRIPTSESSION.containsKey(userCode)) {
        		scriptSessionId.add(CodeRepositoryManager.USERCODE_SCRIPTSESSION.get(userCode));
        	}
        }

        return scriptSessionId;
    }
}
