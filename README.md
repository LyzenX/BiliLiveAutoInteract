# BiliLiveAutoInteract
[![version](https://img.shields.io/github/v/release/MociLSeng/BiliLiveAutoInteract)](https://github.com/MociLSeng/BiliLiveAutoInteract/releases)
[![License](https://img.shields.io/github/license/MociLSeng/BiliLiveAutoInteract?logo=mit)](https://opensource.org/licenses/MIT)
![CodeSize](https://img.shields.io/github/languages/code-size/MociLSeng/BiliLiveAutoInteract)

## 软件功能介绍

可以实现批量b站直播间粉丝团自动打卡(每日弹幕+点赞+分享直播间)，且每天凌晨0点自动重置，可以放服务器上挂着一劳永逸。

其中自动发送点赞功能不会发送“点赞”的弹幕，避免社死。

## 使用方法

需要安装java，如果是Windows用户且不想手动安装java可下载with_runtime版。

运行方法：Windows下运行start.bat，Linux下使用指令`java -jar BiliLiveAutoInteract.jar`。

第一次运行会生成config.json文件，需要先进行配置，完成配置后再次运行。

一个正确的配置文件大概长这样：

```json
{
    "accessKey":"f9**************************0b51",
    "sendLike": true,
    "saveLog": true,
    "danmu": "(=^_^=)",
    "danmuAndLikeRooms": [
        "22473359",
        "22940304"
    ],
    "shareRooms": [
        "1319456",
        "21683315"
    ]
}
```
- 其中，accessKey用于身份验证，请不要泄露，否则别人可能会用它登陆你的账号。
- release中附带了[XiaoMiku01大佬开发的accessKey获取工具](https://github.com/XiaoMiku01/fansMedalHelper/releases/tag/logintool)
- sendLike代表是否发送点赞，如果你无法获取accessKey，可以把它改为false。
- saveLog代表是否保存日志，如果软件出现故障，你可以提交日志来帮助故障修复。你也可以关闭它来避免占用储存空间。
- danmu指发送的弹幕内容。
- danmuAndLikeRooms代表需要发送每日弹幕和点赞的直播间列表。
- shareRooms代表需要分享的直播间列表。
- danmuAndLikeRooms和shareRooms中填写的数字是直播间ID，不是主播uid。

## 亲密度获取方法介绍

b站在5月23日起更新了直播间粉丝团亲密度和小心心的获取方式。

> - [小心心升级公告](https://link.bilibili.com/p/eden/news#/newsdetail?id=2780) <br>
> - [亲密度获取详情](https://www.bilibili.com/read/cv16760012)

要特别指出的是，小心心和亲密度不是同一个东西，他们有不同的获取方式。
比如观看直播30分钟可获取100亲密度，而小心心是每观看5分钟获得100个，最多30分钟。

小心心目前似乎仅参与排名，不影响亲密度的获取，每周日清空。所以小心心目前没有什么用处，只有亲密度可以用来提升粉丝团等级，所以这里只做亲密度任务。

亲密度任务大致如下：
> 每日弹幕，+100点亲密度 <br>
> 每日3次点赞直播间，每次+200点亲密度，每次点赞约10秒CD <br>
> 每日5次分享直播间，每次+100点亲密度，~~每次分享约10分钟CD~~(5月25日改为了约5s CD) <br>
> 观看30分钟直播，+100点亲密度 <br>

前三个任务不要求主播开播，最后一个则需要主播开播才能完成。所以，我们主要做前三个任务，就能获得1200点亲密度。

另外，每个直播间的任务都是独立的，也就是说如果你同时有多个粉丝团，每个都能获取1200点亲密度。

~~这里面最难的是分享任务，因为每次分享有10分钟CD，而且这个时间是全直播间共享的，也就是说，如果你分享了A直播间，去到B直播间也得等这10分钟结束才能再次分享。
除去第一次分享，每完成一个直播间的五次分享就需要50分钟时间，理论上一天最多可以完成28个直播间的分享任务，所以如果你的粉丝团超过28个，就得做出取舍。~~
(5月25日改为了约5s CD)

## 相关链接
待更新

[![forthebadge](https://forthebadge.com/images/badges/contains-cat-gifs.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/built-with-love.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/powered-by-coffee.svg)](https://forthebadge.com)
