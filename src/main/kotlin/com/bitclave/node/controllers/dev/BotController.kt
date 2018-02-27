package com.bitclave.node.controllers.dev

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture

@RestController()
@RequestMapping("/dev/bot/")
class BotController {

    /**
     * Returns ID (Public Key) of the bot, where bot is identified by it’s name.
     * @param name - name of the bot (example Adam).
     *
     * @return String ID (Public Key) of the bot, specified by name.
     * if name is not found then empty String is returned. Http status - 200.
     */
    @RequestMapping(method = [RequestMethod.GET], value = ["{name}"])
    fun getPublicKey(@PathVariable("name") name: String): CompletableFuture<String> {
        return CompletableFuture.completedFuture(
                if ("Adam".equals(name, true))
                    "038d4a758b58137ee47993ca434c1b797096536ada167b942f7d251ed1fc50c1c1"
                else
                    ""
        )
    }

}
