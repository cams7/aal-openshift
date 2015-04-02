#Para clonar a aplicação do openshift, execute o comando:
rhc git-clone aal

#Dentro do diretorio 'aal'. Para visualizar as aplicações do openshift, execute o comando:
rhc app show

#Dentro do diretorio 'aal'. Para visualizar os logs da aplicação no openshift, execute o comando:
rhc tail

#Dentro do diretorio 'aal'. Para redirecionar as portas do openshift, execute o comando:
rhc port-forward

#Dentro do diretorio 'aal'. Para fazer o push no openshift, execute o comando:
git push

#Dentro do diretorio 'aal'. Para fazer o pull do openshift, execute o comando:
git pull

---------------------------------------------------------------------------------------------------

#Dentro do diretorio 'aal'. Para manter o repositorio do github e openshift sincronizados, execute o comando:
git remote add github https://github.com/cams7/aal-openshift.git

#Dentro do diretorio 'aal'. Para fazer o push no github, execute o comando:
git push github master	

#Dentro do diretorio 'aal'. Para fazer o pull do github, execute o comando:
git pull github master



