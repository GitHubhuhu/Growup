

# Git 常用命令

[TOC]

## Git简介

官网：<https://git-scm.com/documentation> 

GUI工具：

- Source Tree
- TortoiseGit



Git项目文件结构：

![1563437581316](C:\Users\lwx745500\AppData\Roaming\Typora\typora-user-images\1563437581316.png)



## 最常用

```shell
git add [file] 		# 把文件从工作区添加到暂存区
git add . 			# 添加所有文件到暂存区

git commit -m '提交说明'	      # 提交修改

git pull                         # 抓取远程仓库所有分支更新并合并到本地
git pull --no-ff                 # 抓取远程仓库所有分支更新并合并到本地，不要快进合并
git fetch origin [远程分支]       # 抓取远程仓库更新
git merge FETCH_HEAD			 # 合并拉取的远程分支


# 推送，如果本地分支是空，则是删除远程分支
git push origin [分支名称]:[远程分支名称]   
# 强制推送，覆盖远程分支
git push origin -f [本地分支]:[远程分支] 

#从指定远程分支创建一个本地新分支
git checkout -b local-branchname origin/remote_branchname  
#指定本地分支和远程分支连接
git branch --set-upstream develop origin/develop 
git branch --set-upstream-to=origin/develop develop

git branch [branch] #新增一个分支
git branch -a   #查看所有分支
git branch -D  #删除本地分支

git merge [branch] --no-ff #合并指定分支

# 显示远程详细信息
git remote -v 	
查看分支历史
git log --graph --pretty=oneline --abbrev-commit

```



## 各种回滚

### 1. 清理工作区[还未add]

```shell
git clean -df 	# 删除没有add的所有目录和文件
```

### 2. 工作区文件回滚[还未add]

放弃工作区中的修改，回滚到指定状态，新增的文件及文件夹不会被删除。

```shell
git checkout .			# 回滚工作区的所有文件
git checkout [file]    	# 回滚工作区的指定文件
git checkout [commit] [file]  # 回滚某个commit的指定文件到暂存区

```

### 3. 从暂存区回滚[已经add]

```shell
git reset .    		# 回滚暂存区的文件到工作区
git reset [file]   	# 回滚暂存区的文件到工作区
git reset --hard   	# 重置暂存区与工作区，与上一次commit保持一致;新增的文件不会清除

git reset --hard [commit] 	# 重置当前分支的HEAD为指定commit，同时重置暂存区和工作区，与指定commit一致
git reset --hard HEAD 		# 撤销还没commit的merge
git reset --hard ORIG_HEAD 	# 撤销已经commit的merge
```

### 4. 已经提交回滚[已经commit]

```shell
git checkout [commit] 	# 回滚到指定的commit，并将指定commit之后的修改放弃
git reset [commit]  	# 回滚到指定的commit，并将指定commit之后的修改放回工作区
git revert [commit] 	# 回滚到指定commit，并新增一个commit用于提交。

```

###  5. 删除已添加到版本管理的文件

本地文件不删除，push后才能删除远程仓库的相应文件

```shell
git rm [filepath] --cached  从版本管理里删除某文件，
git rm -r [filepath] --cached  从版本管理里删除某文件夹
```

## stash 暂存 

如果正在修改代码，同时又需要切换到其它分支修改，就将当前修改的内容暂存起来。

```shell
#暂存
git stash 
#查看暂存列表
git stash list  
#取出暂存信息
git stash pop   
#查看缓存现场
git stash list
#恢复现场
git stash apply stash@{0}
#删除现场缓存
git stash drop stash@{0}
#恢复并删除（这个命令等于 git stash apply stash@{0} 和 git stash drop stash@{0}）
git stash pop stash@{0}
```

## merge cherry-pick 合并 

```shell
git merge [branch]  		# 合并到指定分支
git merge origin/[branch]  	# 合并到某一个远程分支，前提是远程分支先pull到本地
git merge origin/master --no-ff  # 合并master的时候，使用--no-ff,master比较干净。
git cherry-pick [commit]  	# 合并指定的一个提交中的代码
```



## log 查看记录



```shell
git reflog show  查看操作记录
git log
git log <file>      # 查看该文件每次提交记录
git log -p <file>   # 查看每次详细修改内容的diff
git log -p -2       # 查看最近两次详细修改内容的diff
git log --stat      # 查看提交统计信息
git log --pretty=format:"%h %s" --graph  #每个提交所在的分支及其分化衍合情况
```



## diff 比较 

```shell
git diff <file>     # 比较当前文件和暂存区文件差异
git diff
git diff <$id1> <$id2>   # 比较两次提交之间的差异
git diff <branch1>..<branch2> # 在两个分支之间比较
git diff --staged   # 比较暂存区和版本库差异
git diff --cached   # 比较暂存区和版本库差异
git diff --stat     # 仅仅比较统计信息
git diff [版本号] -- README #查看指定版本后README文件所发生的修改  
```

> 冲突说明

```
<<<<<<<HEAD        		   # 标记冲突开始，后面跟的是当前分支中的内容。

HEAD 指向当前分支末梢的提交。

======= 				   # 标记分支分割线

另一条分支上的代码

>>>>>>>[另一个分支id]   		# 标记冲突结束

```

​      

## tag 标签 

```shell
git tag -l #列出所有的标签
git tag -a [tag name] -m '标签说明'  # 创建一个标签，并添加标签的描述
git show [tag name]  展示标签信息
git push origin [tag name] 推送tag到服务器
```



## config 配置 

```shell
git config --global user.name "robbin"  
git config --global user.email "fankai#gmail.com"
git config --global color.ui true
git config --global alias.co checkout
git config --global alias.ci commit
git config --global alias.st status
git config --global alias.br branch
git config --global core.editor "mate -w"    # 设置Editor使用textmate
git config -1 #列举所有配置
```



## remote 远程设置 

```shell

git remote -v                    # 查看远程服务器地址和仓库名称
git remote show origin           # 查看远程服务器仓库状态
git remote add origin git@ github:robbin/robbin_site.git         # 添加远程仓库地址
git remote set-url origin git@ github.com:robbin/robbin_site.git # 设置远程仓库地址(用于修改远程仓库地址)
git remote rm <repository>       # 删除远程仓库
```

## Rebase 让提交更简洁

```shell
#合理使用rebase命令可以使我们的提交历史干净、简洁（难度较大）
git rebase master <branch>       
```





