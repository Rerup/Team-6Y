﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Matematik.Models
{
    public class ExcerciseCategory
    {
        public string Name { get; set; }
        public enum Difficulty {Easy, Medium, Intermediate, Hard, Expert }
    }
}
