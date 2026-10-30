# Activity 2 - DatabaseInitializer, Foreign Keys, Authentication class

## What changed from Activity 1
- `DatabaseConnection`: runs `PRAGMA foreign_keys = ON;` on every connection.
- `DatabaseInitializer` (new, in dao): creates `users` and `activity_logs` (with a foreign key to users) before the menu starts, and seeds a default admin: **username `admin`, password `admin123`, APPROVED** so you can approve others later.
- `Main`: Initialize DB > create UserDao + Scanner > `Authentication.start()`.
- `ui/Authentication` (new): registration (ADMIN, CASHIER, CUSTOMER), login, and role routing. No SQL inside it.
- `AdminMenu`, `CashierMenu`, `CustomerMenu`: role menus (placeholders with Logout for now).
- `MainMenu` from Activity 1 is removed (replaced by Authentication).
- Login is blocked for accounts that are not APPROVED.

## How to pass (checklist)
- [ ] Main.java only does: initialize, create shared UserDao + Scanner, call Authentication.start()
- [ ] One UserDao and one Scanner shared with every menu
- [ ] PRAGMA foreign_keys = ON in DatabaseConnection
- [ ] No database or SQL code in Authentication.java
- [ ] All proposal roles selectable at registration
- [ ] Screenshot 1 and Screenshot 2 (below)

## Screenshots
1. **Startup and registration (IntelliJ Run window):** run Main, choose 1, register a new user (e.g. `maria`, role CUSTOMER), and capture the "Registration successful! ... PENDING" message.
2. **Database:** open students.db > users table > Browse Data. Capture the new row showing status PENDING. (Click the refresh/write-changes if it is not showing.)

## One-time setup (IntelliJ IDEA)
1. Make sure your System Proposal is marked APPROVED/RECORDED by your instructor.
2. File > Open > select this folder. Wait for Maven to download `sqlite-jdbc` (pom.xml). Set Project SDK to Java 17 or higher (records need 16+).
3. Right-click `Main.java` > Run. A `students.db` file appears in the project root.
4. Install DB Browser for SQLite (or SQLiteStudio) to open `students.db` for the database screenshot.

## Push to GitHub (public repo)
1. Create a new PUBLIC repository on GitHub (e.g. `cinepass-oop`).
2. In the IntelliJ terminal:
   ```
   git init
   git add .
   git commit -m "Activity message here"
   git branch -M main
   git remote add origin https://github.com/YOUR_USERNAME/cinepass-oop.git
   git push -u origin main
   ```
3. Check the repo in a private/incognito window to confirm it is public.
4. Paste the repo link in Google Classroom.
   Tip: for each new activity, copy the new files over the same repo and commit again (one repo, new commit per activity).

