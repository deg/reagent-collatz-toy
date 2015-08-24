# Reagent/Re-Frame toy playing with the Collatz Conjecture

This project is ostensibly playing with a math problem, but there is
no real math here; just an excuse to play with React / Reagent /
Re-frame.

See
[https://en.wikipedia.org/wiki/Collatz_conjecture](https://en.wikipedia.org/wiki/Collatz_conjecture)
for details of the underlying math problem (or
[XKCD](https://xkcd.com/710/) for a more apt portrayal).

What we're doing here is a visualization of the start of the inverse relationship.

The first slider specifies how many generations to look backwards from the termination point at 1.
The second slider controls the results table width.

## Possible next steps

- label the sliders
- Clean up the code
- Verify the db schema
- Optimize the calculation not to re-do generations that have already been calculated
- Put a hover text over each number, showing its trajectory down to 1.


## Notes from the lein template

A [re-frame](https://github.com/Day8/re-frame) application designed to ... well, that part is up to you.

### Development Mode

#### Run application:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

#### Run tests:

```
lein clean
lein cljsbuild auto test
```

### Production Build

```
lein clean
lein cljsbuild once min
```
