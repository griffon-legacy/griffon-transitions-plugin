/*
 * Copyright 2009-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the 'License');
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an 'AS IS' BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Andres Almiray
 */
class TransitionsGriffonPlugin {
    // the plugin version
    String version = '1.0.0'
    // the version or versions of Griffon the plugin is designed for
    String griffonVersion = '1.0.0 > *'
    // the other plugins this plugin depends on
    Map dependsOn = ['trident-builder': '1.0.0']
    // resources that are included in plugin packaging
    List pluginIncludes = []
    // the plugin license
    String license = 'Apache Software License 2.0'
    // Toolkit compatibility. No value means compatible with all
    // Valid values are: swing, javafx, swt, pivot, gtk
    List toolkits = ['swing']
    // Platform compatibility. No value means compatible with all
    // Valid values are:
    // linux, linux64, windows, windows64, macosx, macosx64, solaris
    List platforms = []
    // URL where documentation can be found
    String documentation = ''
    // URL where source can be found
    String source = 'https://github.com/griffon/griffon-transitions-plugin'

    List authors = [
        [
            name: 'Andres Almiray',
            email: 'aalmiray@yahoo.com'
        ]
    ]
    String title = 'Animated transitions'
    String description = '''
The Transitions plugin enables animated transitions based on Jeremy's work on [Transition2D][1] and Kirill's [Trident Animation Library][2].
It does so by providing a TransitionLayout capable of handling a Transition2D per child component.
This work is inspired by [Animating CardLayout][3].

Usage
-----

You'll need an instance of `TransitionLayout` in order to add animated transitions to your application. You'll use this class in the same way as
you would `cardLayout()`, the only distinction being the type of object that is used to set the child component's constraints.
When the constraints is a `String` instance then the animated transition will use the layout's default values for `duration` and `transition`. When 
the constraints is a Map it will look for keys `duration` and `transition`, applying default values when no keys are found.

The following nodes will become available on a View script upon installing this plugin

| *Node*           | *Property*        | *Type*       | *Default*         | *Required* | *Bindable* | Notes                              |
| ---------------- | ----------------- | ------------ | ----------------- | ---------- | ---------- | ---------------------------------- |
| transitionLayout | defaultDuration   | long         | 500L              | no         | no         |                                    |
|                  | defaultTransition | Transition2D | BlendTransition2D | no         | no         |                                    |
|                  | mirrorTransition  | boolean      | true              | no         | no         |                                    |
|                  | skipTransitions   | boolean      | false             | no         | no         |                                    |
|                  | beforeCallback    | Runnable     | null              | no         | no         | invoked before a transition starts |
|                  | afterCallback     | Runnable     | null              | no         | no         | invoked after a transition ends    |

The following methods can be used with `TransitionLayout`:

### Navigation

 *  **void first(Container c)**
 *  **void next(Container c)**
 *  **void previous(Container c)**
 *  **void last(Container c)**
 *  **void show(Container c, String name)**

### Queries

 *  **int getCardCount()**
 *  **boolean isAnimating()**
 *  **String cardNameAt(int index)**
 *  **int cardIndexOf(String name)**
 *  **String getCurrentCardName(Container c)**
 *  **int getCurrentCardIndex(Container c)**

TransitionLayout will mirror a transition when navigating a page in reverse. Suppose that `top` is showing page1, if a call to `previous()` is
issued then a reverse transition (from: being page0 and to: being page1) using page0's `transition` will be called. You can change this feature
by setting `mirrorsTransition` (on the layout) to `false`, then when navigating from page1 to page0 results in a forward transition (from: being 
page1 and to: being page0) using page1's `transition` value.
TransitionLayout can skip animating a transition by setting `skipTransitions` to `true`.

There are two new Transition2D classes besides the ones provided by the Transition2D project:

 *  **griffon.transitions.FadeTransition2D** - takes a Color as argument on its constructor
 *  **griffon.transitions.ShapeTransition2D** - takes a Shape and a String as arguments on its constructor

### Example

The following example shows a panel with 3 child components. The first child component will use default values for `duration` and `transition`; 
the second one specifies a different value for its `duration`; lastly the third component defines values for both constraints.

        import java.awt.Color
        import griffon.plugins.transitions.TransitionLayout
        import griffon.plugins.transitions.FadeTransition2D
        import com.bric.image.transition.Transition2D
        import com.bric.image.transition.spunk.SwivelTransition2D
        panel(id: "top", layout: new TransitionLayout(defaultDuration: 1000L,
                 defaultTransition: new SwivelTransition2D(Transition2D.CLOCKWISE)) {
            panel(constraints: "page0") {
                // contents
            }
            panel(constraints: [name: "page1", duration: 1500L]) {
                // contents
            }
            panel(constraints: [name: "page2", duration: 2000L, transition: new FadeTransition2D(Color.BLUE)]) {
                // contents
            }
        }
        swingRepaintTimeline(top, loop: true) // <- DON'T FORGET THIS!

You can trigger a transition by calling one of the following methods on the layout's instance: `next()`, `previous()`, `first()`, `last()`, `show()`.

        top.layout.show(top, "page2")

[1]: http://javagraphics.blogspot.com/2007/04/slideshows-transitions-swf.html
[2]: http://kenai.com/projects/trident/pages/Home
[3]: https://animatingcardlayout.dev.java.net/
'''
}