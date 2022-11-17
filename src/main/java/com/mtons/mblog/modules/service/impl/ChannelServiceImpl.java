package com.mtons.mblog.modules.service.impl;

import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.modules.entity.Post;
import com.mtons.mblog.modules.repository.ChannelRepository;
import com.mtons.mblog.modules.service.ChannelService;
import com.mtons.mblog.modules.entity.Channel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class ChannelServiceImpl implements ChannelService {
	@Autowired
	private ChannelRepository channelRepository;

	@Override
	public List<Channel> findAll(int status) {
		Sort sort = Sort.by(Sort.Direction.DESC, "weight", "id");
		List<Channel> list;
		if (status > Consts.IGNORE) {
			list = channelRepository.findAllByStatus(status, sort);
		} else {
			list = channelRepository.findAll(sort);
		}
//		System.out.println(list);
		return list;
	}

	@Override
	public List<Channel> findChannelSort() {
		List<Channel> list;
		list = channelRepository.findChannels1();
//		System.out.println(list);
		return list;
	}

	@Override
	public List<Channel> findChannelLast() {
		List<Channel> first;
		first= channelRepository.findChannels1();
		List<Channel> all;
		all=channelRepository.findChannelsall();
		List<Channel> last = new ArrayList<>();

		if (first.size()==6){
			for(int i=0 ;i<all.size();i++){
				int temp=0;
				for(int j=0 ;j<first.size();j++){
					if(first.get(j).getId() == all.get(i).getId()){
						temp=1;
					}
				}
				if(temp==0){
					last.add(all.get(i));
				}
			}
		}
		return last;
	}

	@Override
	public Map<Integer, Channel> findMapByIds(Collection<Integer> ids) {
		List<Channel> list = channelRepository.findAllById(ids);
		Map<Integer, Channel> rets = new HashMap<>();
		list.forEach(po -> rets.put(po.getId(), po));
		return rets;
	}

	@Override
	public Channel getById(int id) {
		return channelRepository.findById(id).get();
	}

	@Override
	@Transactional
	public void update(Channel channel) {
		Optional<Channel> optional = channelRepository.findById(channel.getId());
		Channel po = optional.orElse(new Channel());
		BeanUtils.copyProperties(channel, po);
		channelRepository.save(po);
	}

	@Override
	@Transactional
	public void updateWeight(int id, int weighted) {
		Channel po = channelRepository.findById(id).get();

		int max = Consts.ZERO;
		if (Consts.FEATURED_ACTIVE == weighted) {
			max = channelRepository.maxWeight() + 1;
		}
		po.setWeight(max);
		channelRepository.save(po);
	}

	@Override
	@Transactional
	public void delete(int id) {
		channelRepository.deleteById(id);
	}

	@Override
	public long count() {
		return channelRepository.count();
	}

}
