# Git分支管理

* Source Tree
* TortoiseGit



## 分支管理策略

1. master  主分支，用于发布项目

   【固定分支】

2. develop  开发主分支

   【固定分支】

3. feature   功能分支

   【临时分支】

   为了开发某种特定功能，从develop分支上面分出来的

4. Hotfix 或者 bug  

   【临时分支】

   处理bug用的分支，从master上分出来，测试无误后需要合并到master和develop

5. release  测试分支

   【临时分支】

    从develop分出来，测试成功需要合并到master和develop，由master出版本

**命名规范** 

​	a. master 和 develop 分支名固定不可修改。
​	b. 功能分支命名： feature-需求简称
​	c. bug分支命名：  bug-bug编号或者 hotfix-bug编号
​	d. 发布分支命名： release-版本号



## 分支基本原则

1. 所有开发人员都应该在`临时分支`上做开发。
2. 多人协作时，要经常fetch代码，通常在修改代码前需要fetch一次。
3. 不要推送未经过任何测试的代码到远程代码库。
4. 不要在同一个分支上处理多个需求 或 多个bug，养成切换分支工作的习惯。
5. 经常提交，git提交是提交到本地，所以可以随意提交。
6. 在远程代码库，master为稳定分支，develop为不稳定开发分支，其它任何分支都可以在完成合并后删除。
7. 分支命名按规范命名
8. 临时分支用完就删


## Git Flow  工作流

*SourceTree 支持GitFlow*

Git Flow是一个插件，完全遵循以上分支管理策略，避免手动操作出现错误的情况。比如可以避免feature,hostfixes 等分支误合并到 master 的问题。

用 git flow 插件按 feature,release,hotfix,support 等特性来对分支做管理，也避免了命名上的不规范。

具体用法和理念:  http://danielkummer.github.io/git-flow-cheatsheet/index.zh_CN.html

```shell
   usage: git flow <subcommand>

   Available subcommands are:
      init      Initialize a new git repo with support for the branching model.
      feature   Manage your feature branches.
      release   Manage your release branches.
      hotfix    Manage your hotfix branches.
      support   Manage your support branches.
      version   Shows version information.
   
```

