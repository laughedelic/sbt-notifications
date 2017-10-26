package me.penkov.sbt

import sbt._

case object TaskNotifications {

  val defaultScopeMask: ScopeMask = new ScopeMask(
    project = false,
    config = true,
    task = false,
    extra = false
  )

  /** Formats [[TaskKey]] and its [[Scope]] with the given [[ScopeMask]] */
  def formatScopedKey(
    taskKey: TaskKey[_],
    scopeMask: ScopeMask
  ): String =
    Scope.displayMasked(
      taskKey.scope,
      taskKey.key.label,
      scopeMask
    )

  def defaultSuccessFormatter(taskKey: TaskKey[_]): String =
    s"✅ ${formatScopedKey(taskKey, defaultScopeMask)}"
  def defaultFailureFormatter(taskKey: TaskKey[_]): String =
    s"⚠️ ${formatScopedKey(taskKey, defaultScopeMask)}"

  // TODO: move it somewhere
  /** Runs the task and depending on result shows success or failure notification */
  def taskWithNotification[T](
    taskKey: TaskKey[T],
    successFormatter: TaskKey[_] => String,
    failureFormatter: TaskKey[_] => String
  )(notifier: Notifier): Def.Initialize[Task[T]] = taskKey.result.map {
    // Success
    case Value(value) => {
      notifier.notify(
        successFormatter(taskKey),
        value.toString
      )
      value
    }
    // Failure
    case Inc(incomplete) => {
      notifier.notify(
        failureFormatter(taskKey),
        incomplete.causes
          .flatMap(_.directCause)
          .mkString("• ", "\n• ", "")
      )
      throw incomplete
    }
  }

}
