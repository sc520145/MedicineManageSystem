package mms.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import mms.mapper.ClientMapper;
import mms.pojo.Client;
import mms.pojo.EasyUIResult;


/*椤惧service
澶勭悊涓氬姟閫昏緫*/
@Service
@Transactional
public class ClientService {
	@Autowired
	private ClientMapper clientMapper;

	public Client queryClientBycno(String cno) {
		// TODO Auto-generated method stub
		Client client = clientMapper.queryClientBycno(cno);
		return client;
	}

	public String saveClient(Client client) {
		// TODO Auto-generated method stub
		try {
			if (queryClientBycno(client.getCno()) != null) {
				return "此顾客信息已经存在！";
			}
			System.out.println(client);
			clientMapper.saveClient(client);
			return "录入信息成功";
			
		} catch (Exception e) {
			// TODO: handle exception
			return "录入信息失败";
		}

	}

	public EasyUIResult queryAllClient(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		//紧跟在这个方法后的第一个MyBatis 查询方法会被进行分页。
		PageHelper.startPage(page, rows);//获取第page页rows条数据
		List<Client> clients = clientMapper.queryAllClient();//这个查询方法会被分页
		PageInfo<Client> pageInfo = new PageInfo<Client>(clients);//用PageInfo对结果进行封装
		return new EasyUIResult(pageInfo.getTotal(), clients);
	}

	public void deleteClientBycno(String cno) {
		// TODO Auto-generated method stub
		clientMapper.deleteClientBycno(cno);
	}

	public String modifyClient(Client client) {
		// TODO Auto-generated method stub
		Client queryClientBycno = queryClientBycno(client.getCno());
		if (queryClientBycno != null) {
			if (queryClientBycno.getCid() != client.getCid())
				return "杩欎釜瀹㈡埛缂栧彿宸茬粡瀛樺湪锛屼笉鑳戒慨鏀逛负杩欎釜缂栧彿";
		}
		clientMapper.modifyClient(client);
		return "淇敼鎴愬姛";
	}

	public String deleteClientByRows(String[] array) {
		// TODO Auto-generated method stub
		try {
			for (String cno : array) {
				System.out.println(cno);
				deleteClientBycno(cno);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "鎿嶄綔寮傚父锛岃鍒锋柊鍚庢搷浣�";
		}
		return "鍒犻櫎鎴愬姛";
	}

}
