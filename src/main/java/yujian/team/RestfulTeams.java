package yujian.team;

import javax.xml.ws.BindingType;
import javax.xml.ws.Provider;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPBinding;
import javax.xml.ws.http.HTTPException;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.transform.Source;

@WebServiceProvider
@ServiceMode(value=javax.xml.ws.Service.Mode.MESSAGE)
@BindingType(value=HTTPBinding.HTTP_BINDING)
public class RestfulTeams implements Provider<Source>{
	@Resource
	protected WebServiceContext ws_ctx;
	private Map<String,Team> team_map;
	private List<Team> teams;
	private byte[] team_bytes;
	public Source invoke(Source arg0) {
		// TODO Auto-generated method stub
		if(ws_ctx==null) throw new RuntimeException("failed!");
		MessageContext msg_ctx=ws_ctx.getMessageContext();
		String http_verb=(String)msg_ctx.get(MessageContext.HTTP_REQUEST_METHOD);
		http_verb=http_verb.trim().toUpperCase();
		if(http_verb.equals("GET")) return doGet(msg_ctx);
		else throw new HTTPException(405);
	}
	public RestfulTeams(){
		read_teams_from_file();
		deserialize();
	}
	
}
