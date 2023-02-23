# spring5-recipe-app

stay on board please!

it's a CRUD Spring MVC app.
Underneath the covers we are using Spring Data JPA, Hibernate and Thymeleaf.

In pom.xml
So first one on line 30 there we'll bring in the Spring-Boot-Starter-Data-JPA and that's going to bring in a Spring Data JPA and Hibernate. 
We have Spring-Boot-Starter-Thymeleaf that gives us the Thymeleaf template engine.
We have Spring-Boot-Starter-Web that gives us the embedded Tomcat instance.
Then Spring-Boot-Devtools that gives us the auto reload
H2-Database
Project Lombok
We have Spring-Boot-Starter-Test that's what brings in JUnit for tests.

# Commands
Now the form objects we are following command pattern here so
all the forms, like when we made an update to the ingredient. This is the
command object and we are using Project Lombok to set the getters and setters of
this. And we get a no args constructor that means we can get a set of a
constructor with no arguments, and that's all done through Project Lombok, and this
is the command. So what happens here with Spring MVC are
form will bind properties to this and then that gets passed into our
Controller. 

Теперь объекты формы, мы следуем шаблону команд, поэтому
все формы, например, когда мы сделали обновление ингредиента. Это
объект команды, и мы используем Project Lombok для установки геттеров и сеттеров
этого. И мы получаем конструктор no args, который означает, что мы можем получить набор a
конструктор без аргументов, и все это делается через Project Lombok, и это
это команда. Итак, что происходит в Spring MVC
форма свяжет свойства с этим, а затем это будет передано в наш
контроллер.

# Controller

Let's take a look at the IngredientController real quick. So this
are pretty standard Spring MVC Controller. It gets autowired with
several services. So we are following a service layer. We are not injecting the
data layer directly in this. We have that wrapped in a service layer which is I
believe is a best practice.

# Service (interface)

So let's go ahead and take a look at the Ingredient
Service. You can see that it's pretty simple interface we don't have a lot of
complex operations here but we are coding to an interface which is also a
best practice because you saw the tests that we had, so all we got to do is
create a mock of the interface and provide that implementation to our class
well it's under tests. A very very handy way to do that.

# Service (implementation)

Let's go ahead and take a look at the implementation of it. So here's a
actual implementation. This stands as a middle service between the Controller
and the Spring Data JPA Repositories. So we can see that we have this is where we
are going in, getting data out, doing validations, and then returning the
corporate data. So I'm not going to go through everything line by line here.

Давайте посмотрим на его реализацию. Итак, вот
реальная реализация. Это промежуточный сервис между контроллером
и Spring Data JPA Repositories. Итак, мы видим, что у нас есть это место, где мы
входим, получаем данные, выполняем валидацию, а затем возвращаем
корпоративные данные. Поэтому я не буду перечислять все построчно.

#Repository

So we get the RecipeRepository that's going to be what
we wanted. So we can see this extends out the Spring Data JPA
CrudRepository and this gives us a number of operations. Just as a
reminder Spring Data JPA, it's going to provide us in the
implementation at runtime, so we define the interface and Spring Data JPA is
going to use Java reflection to build up and actual implementation at runtime
for us. 

Итак, мы получаем хранилище рецептов (RecipeRepository), которое будет тем, что
что мы хотели. Мы видим, что это расширяет Spring Data JPA
CrudRepository и это дает нам ряд операций. Просто как
напоминание Spring Data JPA, он собирается предоставить нам в
реализацию во время выполнения, поэтому мы определяем интерфейс и Spring Data JPA является
будет использовать отражение Java для создания и фактической реализации во время выполнения
для нас.

# Converters
I am following this for
data type conversion, so this is a Spring standard. This is a standard
interface. It was spring to convert one type to another, so this is another thing
that I'd like to do and this is wired up as a Spring component. 

# Loading Data at Startup
1. data.sql
2. bootstrap 

1. So Spring Boot will look in resources for a file called data.sql, which is
optional. Now we can change that name if we want to, but the default is that.
We can see that's a series of insert statements. So this is going to run every
time on startup. We'll probably want to change this in this course because we
don't want that to run on our MySQL database, so we will change the
initialization of the database and I use that because we want to initialize the
database with different data based on condition. Remember right now, today
we've been using a H2 database and that goes away every time.
So that we're initializing it every time we start up, and then it's just one way

2. So what we have here is a class that implements ApplicationListener and it's listening for a ContextRefreshEvent.
So that's a specific event that gets thrown when the Spring Context starts up.
So that says, we're all started up and everything's ready to go, and then this gets called,
and onApplicationEvent, we call this we call this method, getRecipes(),
which passes back a list of recipes and we save that to the RecipeRepository. And that's what loads up our initial recipes for startup.

Итак, у нас есть класс, который реализует ApplicationListener, и он слушает ContextRefreshEvent.
Это специфическое событие, которое возникает, когда Spring Context запускается. Это означает,
что мы все запустили и все и все готово к работе, а затем вызывается это событие,
и в событии OnApplicationEvent мы вызываем этот метод. вызываем этот метод, getRecipes(),
который передает список рецептов и мы сохраняем его в RecipeRepository. И это то, что загружает наши начальные рецепты для запуска.
