# Development instructions.

## HyperDB
Example of creating a user
```java
 String query = "INSERT INTO users (name, email, \"password\") VALUES (?, ?, ?)";

        try(
Connection conn = DatabaseConfig.getConnection();
PreparedStatement statement = conn.prepareStatement(query)){

        stmt.setString(1,user.getName());
        stmt.setString(2,user.getEmail());
        stmt.setString(3,user.getPassword());

        stmt.executeUpdate();
        
        }catch(SQLException e){
        e.printStackTrace(); //ptrint error
        }
```

example getting users
```java
List<User> users = new ArrayList<>(); //empty array list
String sql = "SELECT id, name, email, \"password\" FROM users";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
```
```java
//this way to get the path from the resources folder
URL mastorasImage = getClass().getResource("/images/mastoras_san.png");
//Labels contain text or/and images
ImageIcon icon = new ImageIcon(mastorasImage);
```