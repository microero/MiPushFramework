# MiPushFramework

在任何非 MIUI 设备上体验小米系统级推送。

![](https://raw.githubusercontent.com/Trumeet/MiPushFramework/master/art/screenshot.png)
![](https://raw.githubusercontent.com/Trumeet/MiPushFramework/master/art/tab_events.jpg)
![](https://raw.githubusercontent.com/Trumeet/MiPushFramework/master/art/tab_permissions.jpg)
![](https://raw.githubusercontent.com/Trumeet/MiPushFramework/master/art/ask.jpg)
![](https://raw.githubusercontent.com/Trumeet/MiPushFramework/master/art/tab_settings.jpg)
![](https://raw.githubusercontent.com/Trumeet/MiPushFramework/master/art/tab_apps.jpg)

从图1可以看出，`PushDemo`（使用小米推送的应用）并未启动后台推送服务，也成功收到了推送。

## 什么是小米系统级推送，为什么会有这个项目

小米推送是小米公司提供的推送服务。就我个人看来非常喜欢这个服务，而且许多App都在使用（如酷安）。

它非常轻量，会在 MIUI 设备上自动启用系统推送，而非 MIUI 设备则在后台保持长连接。



### 系统级推送

类似 GCM，小米推送的系统级推送是在 MIUI 完成的。应用在启动时，会判断如果是 MIUI ROM 则向系统注册推送，推送工作都由系统完成，应用无需后台，更省电。

然而在非 MIUI，每个使用小米推送的应用都会在后台启动一个 `XMPushService`， 10个应用就有10个，20个就有20个服务.. 非常耗电耗内存费流量。



### 本项目的意义

本项目就是想让任何不用MIUI的用户都能用上小米的系统推送，这样既能保证推送，又保证了无需后台。


## 缺陷

* 不支持 MIUI。
* 在 Android O 上，可能由于 **后台限制** 导致推送服务中断，请尝试对 `Xiaomi Push Service Core` 取消后台限制
* 如果使用了绿色守护小米推送处方会无法启动推送服务
* 不建议使用 黑域、绿色守护、Xposed一些模块 对 `Xiaomi Push Service Core` 做操作，可能导致推送不稳定
* 只有推送功能。其他完整功能（如查找手机）请体验 MIUI
* 对 `Xiaomi Push Service Core` 启用电池优化会无法后台



## 优点

* 简单，安装非常简单，无需 Root、Xposed、ROM 支持
* 使用后，其他应用的`XMPushService`会自动禁用，就像在 MIUI
* `XMPushService`禁用后，还能保证推送。
* 更多设置项，可以针对每一款应用设置不同的推送权限
* 完整事件记录，可以监控每个应用的 注册和推送
* 可以在应用程序注册推送时选择是否允许（类似 iOS）
* 拦截小米推送产生的不必要唤醒，也能阻止它读取您的隐私



## 开始使用

安装步骤非常简单 ：

* 前往 Release 标签，下载最新的 Release APK（非 `xmsf_service.apk`），并安装。
* 跟着向导进行设置


## 反馈问题

如需反馈问题，请务必在 Issues 中带上如下内容：

* 你的 ROM 是什么，Android 版本是什么
* 有没有使用框架等工具

同时，请使用 设置， 诊断， 获取日志 获取日志文件，写进 Issue。

## 日志

框架会自动记录日志，保存到私有的缓存目录。暂时不会自动清理，如果需要请手动清除缓存。



## 参与项目

欢迎提交 PR、Issues 帮助这个项目更好。

## 已知问题

* 努比亚ROM应用（第三方使用MiPush的应用）可能不会自动禁用其XMPushService并启动服务，请尝试将框架设为系统应用
* 开发者学生党，开学了更新可能不太及时，请谅解
* 一些通知 Feature 可能无法使用（如通知都会显示为推送框架发出，而不是目标应用 `MIPushNotificationHelper#setTargetPackage`）
* **目前无法收到通知消息，会唤醒目标应用而不在通知栏发送通知，正在修复。**


## 感谢

* @Rachel030219 提供文件
* Android Open Source Project, MultiType, greenDao, SetupWizardLibCompat, Condom, MaterialPreference，GreenDaoUpgradeHelper, epic, Log4a , RichText
* 酷安 @PzHown @lmnm011223 @`苏沐晨风丶`（未采纳） 提供图标

# License

GPL v3，有些狗不遵守开源协议（非本项目），防君子不防小人，请**务必**遵守开源协议！！！
