package by.grsu.publication.service.impl;

import by.grsu.publication.entity.CommentsEntity;
import by.grsu.publication.entity.PeriodicalEntity;
import by.grsu.publication.entity.SubscribeEntity;
import by.grsu.publication.entity.UserEntity;
import by.grsu.publication.model.Comments;
import by.grsu.publication.model.IPeriodical;
import by.grsu.publication.model.IUser;
import by.grsu.publication.model.Periodical;
import by.grsu.publication.model.Subscribe;
import by.grsu.publication.model.User;

public class ServiceConvert {

	public Subscribe convertSub(SubscribeEntity subscribeEnt) {
		Subscribe subscribe = new Subscribe(subscribeEnt.getId(),
				subscribeEnt.getPeriodicalId(), subscribeEnt.getUserId(),
				subscribeEnt.getStatusSubscribe());
		return subscribe;
	}

	public IPeriodical convertPer(PeriodicalEntity periodicalEnt) {
		IPeriodical periodical = null;
		periodical = new Periodical(periodicalEnt.getId(),
				periodicalEnt.getPeriodicalName(), periodicalEnt.getCost(),
				periodicalEnt.getOutputsInMonth(), periodicalEnt.getTopic());
		return periodical;
	}

	public IUser convertUser(UserEntity userEnt) {
		IUser user = new User(userEnt.getId(), userEnt.getLogin(),
				userEnt.getPassword(), userEnt.getMail(),
				userEnt.getUserType(), userEnt.getUserName(),
				userEnt.getUserSurname());
		return user;
	}

	public Comments convertCom(CommentsEntity commEnt) {
		Comments comm = new Comments(commEnt.getId(),
				commEnt.getPeriodicalId(), commEnt.getUserLogin(),
				commEnt.getComm());
		return comm;

	}

}
