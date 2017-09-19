package com.design4music.Rest;

import com.design4music.Domain.TradeItem;
import com.design4music.Service.TradingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nekkyou on 18-9-2017.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/trade")
public class TradingRest {

	private TradingService service;


	public TradingRest(TradingService service) {
		this.service = service;
	}

	@RequestMapping(value = "item", method = RequestMethod.GET)
	public ResponseEntity<TradeItem> getTradeItem(@RequestParam("id") long id) {
		TradeItem item = service.getTradeItem(id);
		return new ResponseEntity<TradeItem>(item, HttpStatus.OK);
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public ResponseEntity<TradeItem> createTradeItem(@RequestParam("accountId") long accId,
													 @RequestParam("flippoId") long flippoId,
													 @RequestParam("otheraccId") long otherAccId,
													 @RequestParam("otherflippoId") long otherflippoId) {
		TradeItem item = service.createTradeItem(accId, flippoId, otherAccId, otherflippoId);
		return new ResponseEntity<TradeItem>(item, HttpStatus.OK);
	}

	@RequestMapping(value = "out", method = RequestMethod.GET)
	public ResponseEntity<List<TradeItem>> getTradesFromAccount(@RequestParam("id") long accountId) {
		List<TradeItem> items = service.getTradesFromAccount(accountId);
		return new ResponseEntity<List<TradeItem>>(items, HttpStatus.OK);
	}

	@RequestMapping(value = "in", method = RequestMethod.GET)
	public ResponseEntity<List<TradeItem>> getTradesForAccount(@RequestParam("id") long accountId) {
		List<TradeItem> items = service.getTradesForAccount(accountId);
		return new ResponseEntity<List<TradeItem>>(items, HttpStatus.OK);
	}

	@RequestMapping(value = "accepted", method = RequestMethod.GET)
	public ResponseEntity<List<TradeItem>> getAllUnhandledAcceptedTrades(@RequestParam("id") long accountId) {
		List<TradeItem> items = service.getUnhandledAcceptedTrades(accountId);
		return new ResponseEntity<List<TradeItem>>(items, HttpStatus.OK);
	}

	@RequestMapping(value = "declined", method = RequestMethod.GET)
	public ResponseEntity<List<TradeItem>> getDeclinedTrades(@RequestParam("id") long accountId) {
		List<TradeItem> items = service.getDeclinesTrades(accountId);
		return new ResponseEntity<List<TradeItem>>(items, HttpStatus.OK);
	}

	@RequestMapping(value = "respond", method = RequestMethod.POST)
	public void respondToTrade(@RequestParam("tradeId") long tradeId,
								@RequestParam("reponse") boolean response) {
		service.respondToTrade(tradeId, response);
	}
}
