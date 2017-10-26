package me.penkov.sbt

import sbt._, Keys._, complete.DefaultParsers._
import me.penkov.sbt.TaskNotifications._

object NotificationsPlugin extends AutoPlugin {

  lazy val defaultNotifier: Notifier = {
    Seq(
      ToastNotifier,
      LibNotifier,
      MacNotifier
    )
    .find(_.isAvailable)
    .getOrElse(NullNotifier)
  }

  object autoImport {
    val notifyFailureOnly = settingKey[Boolean]("Send notifications only if tests failed")

    /** Wraps given task into a task with notification */
    def notifyOn[T](
      taskKey: TaskKey[T],
      successFormatter: TaskKey[_] => String,
      failureFormatter: TaskKey[_] => String
    ): Setting[Task[T]] = {
      taskKey := taskWithNotification(
        taskKey,
        successFormatter,
        failureFormatter
      )(defaultNotifier).value
    }

    /** Same `notifyOn` but with default formatters */
    def notifyOn[T](taskKey: TaskKey[T]): Setting[Task[T]] =
      notifyOn(taskKey, defaultSuccessFormatter, defaultFailureFormatter)
  }
  import autoImport._

  override lazy val projectSettings = Seq(
    testListeners += new NotifierTestListener(name.value, notifyFailureOnly.value),
    notifyFailureOnly := false
  )

  override def trigger = AllRequirements
}
