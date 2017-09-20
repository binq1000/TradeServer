package com.design4music.DAO;

import com.design4music.Domain.TradeItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Nekkyou on 20-9-2017.
 */
public interface TradingDaoCrud extends CrudRepository<TradeItem, Long> {
	List<TradeItem> findAllByProposerId(long id);
	List<TradeItem> findAllByRecieverId(long id);
	List<TradeItem> findAllByResponseAndProposerIdAndHandled(int response, long accountId, boolean handled);
}
