package br.com.appweb.client;

import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ClientApi {
	
	private static final String APPLICATION = "application/json";

	public <T> T save(T t, String api) throws Exception {
		ClientResponse response =null;
		Object entity = null;
		try {
			Client cliente = Client.create();
			WebResource webResource = cliente.resource(api);
			Gson gson = new Gson();
			response = webResource.accept(APPLICATION).type(APPLICATION).post(ClientResponse.class,gson.toJson(t));
			int status = response.getStatus();
			if (status == 200 || status == 201) {
				entity = response.getEntity(t.getClass());
			} else {
				throw new Exception("Ocorreu erro ao gravar,status: " + String.valueOf(status));
			}
		} catch (Exception e) {
			if(response !=null)response.close(); 
			throw new Exception("Ocorreu erro ao gravar", e);
		}finally {
			if(response !=null)response.close(); 
		}
		
		return (T) entity;
	}

	public <T> T update(T t, Long id, String api) throws Exception {
		ClientResponse response =null;
		Object entity =null;
		try {
			Client c = Client.create();
			WebResource webResource = c.resource(api.concat(String.valueOf(id)));
			Gson gson = new Gson();
			response = webResource.accept(APPLICATION).type(APPLICATION).put(ClientResponse.class,gson.toJson(t));
			int status = response.getStatus();
			if (status == 200 || status == 201) {
				entity = response.getEntity(t.getClass());
			} else {
				throw new Exception("Ocorreu erro ao atualizar, status: " + String.valueOf(status));
			}
		} catch (Exception e) {
			if(response !=null)response.close();
			throw new Exception("Ocorreu erro ao gravar", e);
		}finally {
			if(response !=null)response.close();
		}
		return (T) entity;
	}
	
	public int remove(Long id, String api) throws Exception {
		ClientResponse response =null;
		try {
			Client c = Client.create();
			WebResource webResource = c.resource(api.concat(String.valueOf(id)));
			response = webResource.accept(APPLICATION).type(APPLICATION).delete(ClientResponse.class);
			int status = response.getStatus();
			if (!(status == 200 || status == 201 || status == 204)) {
				throw new Exception("Ocorreu erro remover, status: " + String.valueOf(status)); 
			}
		} catch (Exception e) {
			if(response !=null)response.close();
			throw new Exception("Ocorreu erro ao remover", e);
		}finally {
			if(response !=null) response.close();
		}
		
		return 1;
	}

	public <T> List<T> all(Class<T> classe, String api) throws Exception {
		ClientResponse response =null;
		List<T> entity =null;
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(api);
			response = webResource.accept(APPLICATION).type(APPLICATION).get(ClientResponse.class);
			String json = response.getEntity(String.class);
			if (!(response.getStatus() == 200 || response.getStatus() == 201)) {
				throw new Exception("Ocorreu erro ao recuperar, status" + String.valueOf(response.getStatus()));
			} 
			entity = jsonConverteToListObject(json, classe);
		} catch (Exception e) {
			throw new Exception("Ocorreu erro ao recuperar, erro original: ", e);
		}finally {
			if(response !=null)response.close(); 
		}
		return entity;
	}

	public <T> T get(Class<T> classe, Long id, String api) throws Exception {
		ClientResponse response =null;
		T entity = null;
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(api.concat(String.valueOf(id)));
			response = webResource.accept(APPLICATION).type(APPLICATION).get(ClientResponse.class);
			String json = response.getEntity(String.class);
			if (!(response.getStatus() == 200 || response.getStatus() == 201)) {
				throw new Exception("Ocorreu erro ao recuperar, status" + String.valueOf(response.getStatus()));
			} 
			entity = jsonConvertObject(json, classe);
		} catch (Exception e) {
			throw new Exception("Ocorreu erro ao gravar", e);
		}finally {
			if(response !=null)response.close(); 
		}
		return entity;
	}
	
	private <T> List<T> jsonConverteToListObject(String jsonList, Class<T> clazz) {
	    Type typeOfT = TypeToken.getParameterized(List.class, clazz).getType();
	    return new Gson().fromJson(jsonList, typeOfT);
	}
	
	private <T> T jsonConvertObject(String json, Class<T> classe) {
		return new Gson().fromJson(json, classe);
	}
}