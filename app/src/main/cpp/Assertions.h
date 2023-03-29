<<<<<<< HEAD
#ifndef ASSERTIONS_H
#define ASSERTIONS_H

// MACROS to define to-string and logging function utils
#define STRINGIFY(x) #x
#define FILE_AND_LINE __FILE__ ":" STRINGIFY(__LINE__)
=======
#pragma once

// MACROS to define to-string and logging function utils
#define STRINGIFY(x) #x
#define FILE_AND_LINE_INTERNAL(LINE) __FILE__ ":" STRINGIFY(LINE)
#define FILE_AND_LINE FILE_AND_LINE_INTERNAL(__LINE__)
>>>>>>> a875aeafd5aca1e9da365dbec648a515ab7c75b9

#define THROW(msg) Throw(msg, nullptr, FILE_AND_LINE);

#define CHECK(exp)                                      \
    {                                                   \
        if (!(exp)) {                                   \
            Throw("Check failed", #exp, FILE_AND_LINE); \
        }                                               \
    }
#define CHECK_MSG(exp, msg)                  \
    {                                        \
        if (!(exp)) {                        \
            Throw(msg, #exp, FILE_AND_LINE); \
        }                                    \
    }

#define ASSERT(exp)                         \
{                                           \
  if (!(exp)) {                             \
    throw std::logic_error(FILE_AND_LINE);  \
  }                                         \
}
<<<<<<< HEAD

#endif // ASSERTIONS_H
=======
>>>>>>> a875aeafd5aca1e9da365dbec648a515ab7c75b9
