# 🚨 Unmaintained 🚨

**This project is no longer actively maintained.**  
Feel free to fork it if you'd like to continue its development.

# Recipe Scrappers

Java package to scrap recipes from websites which follow Schema.org [recipe schema](https://schema.org/Recipe).

This project is a port of corresponding [python package](https://github.com/hhursev/recipe-scrapers) but don't guarantee to be in sync.

### Warning! This package is still in Beta and not yet published to Maven Central.

## Show me the code

```java
import com.prashantchaubey.recipescrappers.RecipeScrapper;
import com.prashantchaubey.recipescrappers.RecipeScrapperFactory;

class ExampleApp {
    public static void main(String[] args) {
        RecipeScrapper scrapper = RecipeScrapperFactory.get("your url");
        System.out.println(scrapper.getTitle());
        
        //If you want to suppress any exception during scrapping and get default values
        scrapper = RecipeScrapperFactory.get("your url", true);
        System.out.println(scrapper.getTotalTime());
    }
}
```

## Scrappers available for
* [https://www.acouplecooks.com/](https://www.acouplecooks.com/)

## Support

If you need help feel free to create an issue, and I will reach out ASAP

## Want to contribute

Read [this](CONTRIBUTING.md).
