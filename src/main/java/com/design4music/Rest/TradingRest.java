package com.design4music.Rest;

import com.design4music.Domain.TradeItem;
import com.design4music.Service.TradingService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

	@ApiOperation(value = "getTrades", notes = "Get all the trades that have yet to get a response")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<TradeItem>> getTrades() {
		List<TradeItem> items = service.getAllTrades();
		return new ResponseEntity<List<TradeItem>>(items, HttpStatus.OK);
	}

	@ApiOperation(value = "getTradeItem", notes = "Get a single TradeItem by id")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "TradeItem's Id", required = true, dataType = "long", paramType = "query")
	})
	@RequestMapping(value = "item", method = RequestMethod.GET)
	public ResponseEntity<TradeItem> getTradeItem(@RequestParam("id") long id) {
		TradeItem item = service.getTradeItem(id);
		return new ResponseEntity<TradeItem>(item, HttpStatus.OK);
	}

	@ApiOperation(value = "createTradeItem", notes = "Create a TradeItem with the parameters")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "accountId", value = "Proposer's Id", required = true, dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "flippoId", value = "Proposer's offered flippo Id", required = true, dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "otheraccId", value = "Receiver's Id", required = true, dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "otherflippoId", value = "Receiver's offered flippo Id", required = true, dataType = "long", paramType = "query")
	})
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public ResponseEntity<TradeItem> createTradeItem(@RequestParam("accountId") long accId,
													 @RequestParam("flippoId") long flippoId,
													 @RequestParam("otheraccId") long otherAccId,
													 @RequestParam("otherflippoId") long otherflippoId) {
		TradeItem item = service.createTradeItem(accId, flippoId, otherAccId, otherflippoId);
		return new ResponseEntity<TradeItem>(item, HttpStatus.OK);
	}

	@ApiOperation(value = "getTradesFromAccount", notes = "Get all trades that the account is the proposer for.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "Proposer's Id", required = true, dataType = "long", paramType = "query")
	})
	@RequestMapping(value = "out", method = RequestMethod.GET)
	public ResponseEntity<List<TradeItem>> getTradesFromAccount(@RequestParam("id") long accountId) {
		List<TradeItem> items = service.getTradesFromAccount(accountId);
		return new ResponseEntity<List<TradeItem>>(items, HttpStatus.OK);
	}

	@ApiOperation(value = "getTradesForAccount", notes = "Get all trades that the account is the receiver for.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "Receiver's Id", required = true, dataType = "long", paramType = "query")
	})
	@RequestMapping(value = "in", method = RequestMethod.GET)
	public ResponseEntity<List<TradeItem>> getTradesForAccount(@RequestParam("id") long accountId) {
		List<TradeItem> items = service.getTradesForAccount(accountId);
		return new ResponseEntity<List<TradeItem>>(items, HttpStatus.OK);
	}

	@ApiOperation(value = "getAllUnhandledAcceptedTrades", notes = "Get all the trades that you are the proposer for that have been accepted.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "Proposer's Id", required = true, dataType = "long", paramType = "query")
	})
	@RequestMapping(value = "accepted", method = RequestMethod.GET)
	public ResponseEntity<List<TradeItem>> getAllUnhandledAcceptedTrades(@RequestParam("id") long accountId) {
		List<TradeItem> items = service.getUnhandledAcceptedTrades(accountId);
		return new ResponseEntity<List<TradeItem>>(items, HttpStatus.OK);
	}

	@ApiOperation(value = "getAllTradeResponses", notes = "Get the trade history of the account")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "Account's Id", required = true, dataType = "long", paramType = "query")
	})
	@RequestMapping(value = "history", method = RequestMethod.GET)
	public ResponseEntity<List<TradeItem>> getAllTradeResponses(@RequestParam("id") long accountId) {
		List<TradeItem> items = service.getAllTradeResponses(accountId);
		return new ResponseEntity<List<TradeItem>>(items, HttpStatus.OK);
	}

	@ApiOperation(value = "getDeclinedTrades", notes = "Get all trades that you are the proposer for that have been declined")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "Proposer's Id", required = true, dataType = "long", paramType = "query")
	})
	@RequestMapping(value = "declined", method = RequestMethod.GET)
	public ResponseEntity<List<TradeItem>> getDeclinedTrades(@RequestParam("id") long accountId) {
		List<TradeItem> items = service.getDeclinesTrades(accountId);
		return new ResponseEntity<List<TradeItem>>(items, HttpStatus.OK);
	}

	@ApiOperation(value = "respondToTrade", notes = "Respond to a trade")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "tradeId", value = "TradeItem's Id", required = true, dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "response", value = "Accepted/Declined", required = true, dataType = "int", paramType = "query")
	})
	@RequestMapping(value = "respond", method = RequestMethod.POST)
	public void respondToTrade(@RequestParam("tradeId") long tradeId,
								@RequestParam("response") int response) {
		boolean responseBool = (response == 1);
		service.respondToTrade(tradeId, responseBool);
	}
}
