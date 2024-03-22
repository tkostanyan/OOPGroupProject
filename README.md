# OOPGroupProject


[//]: # (For now we have Casino class which is kind of the game board. )

[//]: # (Then we have user class and we want to store the user history in the Casino instance to track the balance.)

[//]: # (We want to give a user the ability to log out, and log back in and continue with the same balance, if the instance of   )

[//]: # (main App is not shut down.)

[//]: # ()
[//]: # (Then we have games which inherit from the BaseGame class, and users will be able to see the available games and play them)

[//]: # (with their current balance. )

[//]: # ()
[//]: # (For now the every registered user received 100. balance and it's not possible to deposit "money".)


# Casino Project Overview

## Introduction

This project aims to develop a casino application using Java, consisting of several classes to manage the game board, users, and different games available in the casino. The primary goals include:

1. **Implementing the Casino Class**: The `Casino` class serves as the game board where users can access various games and manage their balances.

2. **User Class and Balance Tracking**: The `User` class manages user information and tracks their balance. User history is stored in the `Casino` instance to maintain continuity across a single session.

3. **User Authentication and Session Management**: Users can log in, log out, and resume playing with the same balance across a single session.

4. **Game Implementation**: Games inherit from the `BaseGame` class, allowing users to view available games and play them with their current balances.

5. **Initial Balance and Deposits**: Upon registration, every user receives an initial balance of 100 units. However, depositing additional money is not implemented in this version.

## Casino Class

The `Casino` class manages games, users, and their balances. It facilitates user authentication, session management, and game access.

## User Class

The `User` class represents individual users of the casino application. It stores user information such as username, password, and balance.

## BaseGame Class

The `BaseGame` class serves as the base class for all games available in the casino. It defines common functionalities and properties that all games share.

## Conclusion

The casino project aims to create an engaging and user-friendly application for playing various games. By implementing classes for the casino, users, and games, we can provide a seamless experience for players while maintaining robustness and scalability in the codebase.
