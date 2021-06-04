# Contributing

Use `google-java-format` for code formatting. You can download plugins for your corresponding IDEs.

1. Clone the repo
2. Create a scrapper class extending `AbstractRecipeScrapper` in the root package along with other scrappers. `com.prashantchaubey.recipescrappers`
3. Add a sample recipe page html to `src/test/resources/data/*.html`
4. Configure this html for testing [here](https://github.com/pc9795/recipe-scrappers/blob/master/src/test/java/com/prashantchaubey/recipescrappers/providers/TestRecipeHtmlContentProvider.java#L25).
5. Create a test for your scrapper like this [one](https://github.com/pc9795/recipe-scrappers/blob/master/src/test/java/com/prashantchaubey/recipescrappers/ACoupleCooksScrapperTest.java) to validate everything is working.
