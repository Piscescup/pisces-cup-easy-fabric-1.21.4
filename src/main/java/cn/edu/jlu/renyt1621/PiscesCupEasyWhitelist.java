package cn.edu.jlu.renyt1621;

import cn.edu.jlu.renyt1621.commands.*;
import cn.edu.jlu.renyt1621.config.Config;
import cn.edu.jlu.renyt1621.translate.Translate;
import cn.edu.jlu.renyt1621.utils.PathUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import static cn.edu.jlu.renyt1621.References.MOD_ID;
import static cn.edu.jlu.renyt1621.References.MOD_LOGGER;

public class PiscesCupEasyWhitelist implements ModInitializer {

	@Override
	public void onInitialize() {
		MOD_LOGGER.info("Hello Fabric world!");
		MOD_LOGGER.info("Hello, " + MOD_ID);


		Config.INSTANCE.load();
		Translate.handleResourceReload(Config.INSTANCE.getLang());

		CommandRegistrationCallback.EVENT.register(
			CommandRegisterImpl.INSTANCE::registerCommands
		);

	}
}