// SPDX-License-Identifier: MIT
// Copyright (c) 2020 Hadrien Chauvin

package fr.aphp.wind.eds.generator

import com.holdenkarau.spark.testing.DataFrameSuiteBase
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CoreSpec extends AnyFlatSpec with Matchers with DataFrameSuiteBase {

  behavior of "uuidString"

  it should "generate hexadecimal uuids" in {
    import spark.implicits._

    val id = Seq("foo")
      .toDF("foo")
      .withColumn("id", uuidString)
      .collect()(0)
      .getAs[String]("id")

    id should fullyMatch regex ("[0-9A-F]+")
  }

  behavior of "uuidLong"

  it should "generate numeric uuids" in {
    import spark.implicits._

    val id = Seq("foo")
      .toDF("foo")
      .withColumn("id", uuidLong)
      .collect()(0)
      .getAs[Long]("id")

    id should be > 0L
  }
}
