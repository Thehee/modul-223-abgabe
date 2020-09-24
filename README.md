# Read me!

### Was kann diese Applikation?
Es gibt zwei verschiedene Rollen, ein Admin und beliebig viele Benutzer.
Der Erste, der sich anmeldet, kriegt die Rolle des Admins, danach werden alle nur noch Benutzer.
Benutzer können in dieser Applikation Zeitspannen erstellen und die mit Tags oder Kategorien austatten.
Der Admin kann die Benutzer, Tags und Kategorien verwalten.

### Wie startet man die Applikation

Als Erstes muss man eine mysql Datenbank erstellen mit dem namen "webdemo".

<pre>CREATE DATABASE webdemo;</pre>

Als Nächstes muss man den DB Benutzer "modul223" erstellen mit dem Passwort "123".

<pre>CREATE USER 'modul223'@'localhost' IDENTIFIED BY '123'; </pre>

Und zuletzt muss man diesem user auch die Rechte auf die webdemo db geben.

<pre>
GRANT ALL PRIVILEGES ON webdemo.* TO 'modul223'@'localhost';
</pre>

Nachdem man dies gemacht hat kann man die Applikation starten.
